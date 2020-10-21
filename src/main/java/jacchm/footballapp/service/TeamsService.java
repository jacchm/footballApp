package jacchm.footballapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import jacchm.footballapp.customexceptions.ExternalFootballApiConnectionException;
import jacchm.footballapp.mapping.dto.TeamDTO;
import jacchm.footballapp.mapping.inputs.TeamsInput;
import jacchm.footballapp.mapping.mapper.TeamMapper;
import jacchm.footballapp.model.entity.Team;
import jacchm.footballapp.repository.TeamRepository;
import jacchm.footballapp.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TeamsService {

    private final ExternalFootballAPIService externalFootballAPIService;

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    @Value("${COMPETITIONS_ID}")
    private final List<Integer> competitionIdList;

    @PostConstruct
    private void printCompetitionsIds(){
        System.out.println(competitionIdList);
    }

    public boolean deleteAll() {
        teamRepository.deleteAll();
        return true;
    }

    public List<TeamDTO> getAllLeagueTeams(Integer competitionId) {
        List<TeamDTO> allLeagueTeamsDTO = new ArrayList<>();

        for (Team team : teamRepository.findByCompetitionID(competitionId)) {
            allLeagueTeamsDTO.add(teamMapper.teamToTeamDto(team));
        }

        return allLeagueTeamsDTO;
    }

    public boolean updateAll() {
        for (int i = 0; i < competitionIdList.size(); i++) {
            int competitionId = competitionIdList.get(i);
            try {
                JsonNode node = JsonUtil.parse(externalFootballAPIService.getTeams(competitionId));
                TeamsInput teamsInput = JsonUtil.fromJson(node, TeamsInput.class);

                saveInDataBase(teamsInput);
            } catch (JsonProcessingException e) {
                log.error("Error has been encountered during JSON parsing. Competition id: " + competitionId, e);
                return false;
            } catch (ExternalFootballApiConnectionException e) {
                log.error("Error has been encountered when connecting to external football API. Competition id: " +
                        competitionId, e);
                return false;
            }
        }

        return true;
    }

    private void saveInDataBase(TeamsInput teamsInput) {
        Integer competitionId = teamsInput.getCompetition().getId();
        for (TeamDTO teamDTO : teamsInput.getTeams()) {
            teamDTO.setCompetitionId(competitionId);
            Team team = teamMapper.teamDtoToTeam(teamDTO);
            teamRepository.save(team);
        }
    }

}
