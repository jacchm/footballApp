package jacchm.footballapp.service.impl;

import jacchm.footballapp.customexceptions.ExternalFootballApiConnectionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.text.MessageFormat;
import java.util.Date;

@Slf4j
@Service
public class ExternalFootballAPIService {

    private final String FOOTBALL_API_URL = "http://api.football-data.org";
    private final HttpMethod HTTP_METHOD = HttpMethod.GET;
    private final String AUTH_TOKEN;
    private final String AUTH_HEADER = "X-Auth-Token";

    public ExternalFootballAPIService(@Value("${AUTH_TOKEN}") String auth_token) {
        AUTH_TOKEN = auth_token;
    }

    public String getCompetitions() throws ExternalFootballApiConnectionException {
        String url = FOOTBALL_API_URL + "/v2/competitions/";

        return getResponse(getRequestEntity(url));
    }

    public String getTeams(int competitionId) throws ExternalFootballApiConnectionException {
        String url = MessageFormat.format(FOOTBALL_API_URL + "/v2/competitions/{0}/teams",
                Integer.toString(competitionId));

        return getResponse(getRequestEntity(url));
    }

    public String getResults(int competitionId) throws ExternalFootballApiConnectionException {
        String url = MessageFormat.format(FOOTBALL_API_URL + "/v2/competitions/{0}/standings",
                Integer.toString(competitionId));

        return getResponse(getRequestEntity(url));
    }

    private String getResponse(RequestEntity request) throws ExternalFootballApiConnectionException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(request, String.class);
        log.info("Request to external API has been sent. " + new Date());

        int responseCode = response.getStatusCodeValue();
        if (responseCode < 200 || responseCode > 299) {
            throw new ExternalFootballApiConnectionException("It was not possible to get the response from external football API." +
                    "Response status: " + responseCode);
        }

        return response.getBody();
    }

    private RequestEntity getRequestEntity(String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(AUTH_HEADER, AUTH_TOKEN);

        RequestEntity requestEntity = new RequestEntity(headers, HTTP_METHOD, URI.create(url));

        return requestEntity;
    }

}
