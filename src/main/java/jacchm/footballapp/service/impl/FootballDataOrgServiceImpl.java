package jacchm.footballapp.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jacchm.footballapp.customexceptions.ExternalFootballApiConnectionException;
import jacchm.footballapp.mapping.dto.CompetitionDTO;
import jacchm.footballapp.mapping.dto.LeagueTablePositionDTO;
import jacchm.footballapp.mapping.dto.TeamDTO;
import jacchm.footballapp.service.FootballDataOrgService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FootballDataOrgServiceImpl implements FootballDataOrgService {

    private final String FOOTBALL_DATA_URL;
    private final String FOOTBALL_DATA_VERSION;
    private final HttpHeaders HEADERS;
    private final ObjectMapper objectMapper;

    @Autowired
    public FootballDataOrgServiceImpl(@Value("${FOOTBALL_DATA_URL}") String football_api_url,
                                      @Value("${AUTH_TOKEN}") String auth_token,
                                      @Value("${AUTH_HEADER}") String auth_header,
                                      @Value("${FOOTBALL_DATA_VERSION}") String football_data_org_version, ObjectMapper objectMapper) {
        this.FOOTBALL_DATA_URL = football_api_url;
        this.FOOTBALL_DATA_VERSION = football_data_org_version;
        this.HEADERS = new HttpHeaders();
        HEADERS.add(auth_header, auth_token);
        this.objectMapper = objectMapper;
    }

    @Override
    public List<CompetitionDTO> getCompetitions() throws ExternalFootballApiConnectionException {
        String url = FOOTBALL_DATA_URL + FOOTBALL_DATA_VERSION;
        String response = getResponse(new RequestEntity(HEADERS, HttpMethod.GET, URI.create(url)));

        List<CompetitionDTO> competitionDTOList;

        try {
            competitionDTOList = objectMapper.readValue(objectMapper.readTree(response).get("competitions").toString(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, CompetitionDTO.class));
        } catch (JsonProcessingException e) {
            log.error("Information achieved from the server cannot be mapped. Operation aborted.", e);
            return null;
        }

        return competitionDTOList;
    }

    @Override
    public List<TeamDTO> getTeams(int competitionId) throws ExternalFootballApiConnectionException {
        String url = MessageFormat.format(FOOTBALL_DATA_URL + FOOTBALL_DATA_VERSION + "{0}/teams",
                Integer.toString(competitionId));
        String response = getResponse(new RequestEntity(HEADERS, HttpMethod.GET, URI.create(url)));

        List<TeamDTO> teamDTOList;

        try {
            teamDTOList = objectMapper.readValue(objectMapper.readTree(response).get("teams").toString(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, TeamDTO.class));

            Integer jsonCompetitionId = Integer.parseInt(objectMapper.readValue(objectMapper.readTree(response)
                    .get("competition").get("id").toString(), String.class));

            teamDTOList.stream()
                    .map(teamDTO -> {
                        teamDTO.setCompetitionId(jsonCompetitionId);
                        return teamDTO;
                    });
        } catch (NumberFormatException e) {
            log.info("There is no information about the league the teams are related to. Operation aborted.", e);
            return null;
        } catch (JsonProcessingException e) {
            log.error("Information achieved from the server cannot be mapped. Operation aborted.", e);
            return null;
        }

        return teamDTOList;
    }

    @Override
    public List<LeagueTablePositionDTO> getResults(int competitionId) throws ExternalFootballApiConnectionException {
        String url = MessageFormat.format(FOOTBALL_DATA_URL + FOOTBALL_DATA_VERSION + "{0}/standings",
                Integer.toString(competitionId));
        String response = getResponse(new RequestEntity(HEADERS, HttpMethod.GET, URI.create(url)));

        List<LeagueTablePositionDTO> resultsToBeReturned;

        try {
            Integer jsonCompetitionId = Integer.parseInt(objectMapper.readValue(objectMapper.readTree(response)
                    .get("competition").get("id").toString(), String.class));

            List<JsonNode> jsonStandingsList = objectMapper.readValue(objectMapper.readTree(response).get("standings").toString(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, JsonNode.class));
            resultsToBeReturned = new LinkedList<>();

            for (JsonNode jsonNode : jsonStandingsList) {
                List<LeagueTablePositionDTO> jsonTable = objectMapper.readValue(jsonNode.get("table").toString(),
                        objectMapper.getTypeFactory().constructCollectionType(List.class, LeagueTablePositionDTO.class));
                String type = objectMapper.readValue(jsonNode.get("type").toString(), String.class);

                List<LeagueTablePositionDTO> partialResults = jsonTable.stream().map(leagueTablePositionDTO -> {
                    leagueTablePositionDTO.setType(type);
                    leagueTablePositionDTO.setCompetitionId(jsonCompetitionId);
                    return leagueTablePositionDTO;
                }).collect(Collectors.toList());

                resultsToBeReturned.addAll(partialResults);
            }
        } catch (NumberFormatException e) {
            log.info("There is no information about the league the teams are related to. Operation aborted.", e);
            return null;
        } catch (JsonProcessingException e) {
            log.error("Information achieved from the server cannot be mapped. Operation aborted.", e);
            return null;
        }

        return resultsToBeReturned;
    }

    private String getResponse(RequestEntity request) throws ExternalFootballApiConnectionException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(request, String.class);
        log.info("Request to external API has been sent. " + new Date());

        if (response.getStatusCode().isError()) {
            throw new ExternalFootballApiConnectionException("It was not possible to get the response from external football API." +
                    "Response status: " + response.getStatusCode().value());
        }

        return response.getBody();
    }


}
