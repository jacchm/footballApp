package jacchm.footballapp.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jacchm.footballapp.exception.DataParsingException;
import jacchm.footballapp.exception.ExternalFootballApiConnectionException;
import jacchm.footballapp.mapping.dto.CompetitionDTO;
import jacchm.footballapp.mapping.dto.LeagueTablePositionDTO;
import jacchm.footballapp.mapping.dto.TeamDTO;
import jacchm.footballapp.service.ExternalFootballAPIService;
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
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class FootballDataOrgServiceImpl implements ExternalFootballAPIService {

    private final String footballDataCompetitions;
    private final String footballDataTeams;
    private final String footballDataResults;
    private final HttpHeaders headers;
    private final ObjectMapper objectMapper;
    private final RestTemplate restTemplate;

    public FootballDataOrgServiceImpl(@Value("${footballDataCompetitions}") String footballDataCompetitions,
                                      @Value("${footballDataTeams}") String footballDataTeams,
                                      @Value("${footballDataResults}") String footballDataResults,
                                      @Value("${authToken}") String auth_token,
                                      @Value("${authHeader}") String auth_header,
                                      ObjectMapper objectMapper,
                                      RestTemplate restTemplate) {
        this.footballDataCompetitions = footballDataCompetitions;
        this.footballDataTeams = footballDataTeams;
        this.footballDataResults = footballDataResults;
        this.headers = new HttpHeaders();
        headers.add(auth_header, auth_token);
        this.objectMapper = objectMapper;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<CompetitionDTO> getCompetitions() {
        RequestEntity<String> request = new RequestEntity<>(headers, HttpMethod.GET, URI.create(footballDataCompetitions));
        ResponseEntity<String> response = restTemplate.exchange(request, String.class);

        if (response.getStatusCode().isError()) {
            throw new ExternalFootballApiConnectionException("It was not possible to get the response from external football API." +
                    "Response status: " + response.getStatusCode().value());
        }

        String respCompetitions = response.getBody();
        List<CompetitionDTO> competitionDTOList;

        try {
            competitionDTOList = objectMapper.readValue(objectMapper.readTree(respCompetitions).get("competitions").toString(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, CompetitionDTO.class));
        } catch (JsonProcessingException e) {
            throw new DataParsingException("Json processing error during competitions mapping.", "0001");
        }

        return competitionDTOList;
    }

        @Override
        public List<TeamDTO> getTeams(int competitionId) {
            String url = MessageFormat.format(footballDataTeams, Integer.toString(competitionId));
            RequestEntity<String> request = new RequestEntity<>(headers, HttpMethod.GET, URI.create(url));
            ResponseEntity<String> response = restTemplate.exchange(request, String.class);

            if (response.getStatusCode().isError()) {
                throw new ExternalFootballApiConnectionException("It was not possible to get the response from external football API." +
                        "Response status: " + response.getStatusCode().value());
            }

            String respTeams = response.getBody();
            List<TeamDTO> teamDTOList;

            try {
                teamDTOList = objectMapper.readValue(objectMapper.readTree(respTeams).get("teams").toString(),
                        objectMapper.getTypeFactory().constructCollectionType(List.class, TeamDTO.class));
                Integer jsonCompetitionId = Integer.parseInt(objectMapper.readValue(objectMapper.readTree(respTeams)
                        .get("competition").get("id").toString(), String.class));

                teamDTOList.forEach(teamDTO -> teamDTO.setCompetitionId(jsonCompetitionId));
            } catch (JsonProcessingException e) {
                throw new DataParsingException("Json processing error during teams mapping.", "0001");
            } catch (NumberFormatException e) {
                throw new DataParsingException("Teams mapping failed. " +
                        "No information about the league id.", "0002");
            }

            return teamDTOList;
        }

    @Override
    public List<LeagueTablePositionDTO> getResults(int competitionId) {
        String url = MessageFormat.format(footballDataResults, Integer.toString(competitionId));
        RequestEntity<String> request = new RequestEntity<>(headers, HttpMethod.GET, URI.create(url));
        ResponseEntity<String> response = restTemplate.exchange(request, String.class);

        if (response.getStatusCode().isError()) {
            throw new ExternalFootballApiConnectionException("It was not possible to get the response from external football API." +
                    "Response status: " + response.getStatusCode().value());
        }

        String results = response.getBody();
        List<LeagueTablePositionDTO> resultsDTOList;

        try {
            Integer jsonCompetitionId = Integer.parseInt(objectMapper.readValue(objectMapper.readTree(results)
                    .get("competition").get("id").toString(), String.class));

            List<JsonNode> jsonStandingsList = objectMapper.readValue(objectMapper.readTree(results).get("standings").toString(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, JsonNode.class));
            resultsDTOList = new LinkedList<>();

            for (JsonNode jsonNode : jsonStandingsList) {
                List<LeagueTablePositionDTO> partialResults = objectMapper.readValue(jsonNode.get("table").toString(),
                        objectMapper.getTypeFactory().constructCollectionType(List.class, LeagueTablePositionDTO.class));
                String type = objectMapper.readValue(jsonNode.get("type").toString(), String.class);

                partialResults.forEach(leagueTablePositionDTO -> {
                    leagueTablePositionDTO.setType(type);
                    leagueTablePositionDTO.setCompetitionId(jsonCompetitionId);
                });

                resultsDTOList.addAll(partialResults);
            }
        } catch (JsonProcessingException e) {
            throw new DataParsingException("Json processing error during results mapping",  "0001");
        } catch (NumberFormatException e) {
            throw new DataParsingException("Results mapping failed. " +
                    "No information about the league id.", "0002");
        }

        return resultsDTOList;
    }

}
