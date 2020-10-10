package jacchm.footballapp.service;

import com.fasterxml.jackson.databind.JsonNode;
import jacchm.footballapp.config.InputsConfig;
import jacchm.footballapp.mapping.dto.TeamDTO;
import jacchm.footballapp.mapping.dto.TeamsInputDTO;
import jacchm.footballapp.mapping.mapper.TeamMapper;
import jacchm.footballapp.model.entity.Team;
import jacchm.footballapp.repository.CompetitionRepository;
import jacchm.footballapp.repository.TeamRepository;
import jacchm.footballapp.util.JsonUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class TeamsService {

    private static final Logger logger = LoggerFactory.getLogger(StandingsInputService.class);

    private TeamRepository teamRepository;
    private CompetitionRepository competitionRepository;
    private ExternalFootballAPIService externalFootballAPIService;

    public String deleteAll() {
        teamRepository.deleteAll();
        return "Delete has been completed";
    }

    public List<TeamDTO> getAllLeagueTeams(Integer competitionId) {

        int areaId = competitionRepository.findById(competitionId).get().getArea().getId();

        List<TeamDTO> allLeagueTeamsDTO = new ArrayList<>();

        // TODO: think about correct logic
        for (Team team : teamRepository.findByAreaId(areaId).orElse(new ArrayList<>())) {
            allLeagueTeamsDTO.add(TeamMapper.INSTANCE.teamToTeamDto(team));
        }

        return allLeagueTeamsDTO;
    }

    public String updateAllTeams() {
        String jsonTeamsInput;
        TeamsInputDTO teamsInputDTO = null;
        JsonNode node;
        String path = "src\\main\\resources\\dataFromExternalApi\\";

        for (int i = 0; i < InputsConfig.competitionsId.length; i++) {

            try {
                jsonTeamsInput = externalFootballAPIService.getTeams(InputsConfig.competitionsId[i]);
                node = JsonUtil.parse(jsonTeamsInput);
                teamsInputDTO = JsonUtil.fromJson(node, TeamsInputDTO.class);

                logger.info("Connection with external API has been established. Competitions have been achieved.");
            } catch (IOException e1) {
                // In case that external football API doesn't respond
                logger.error("External Football API hasn't responded. Program will try to achieve the data from file.");
                try {
                    // Trying to achieve last data from file
                    String competitionNumber = Integer.toString(InputsConfig.competitionsId[i]);
                    File file = new File(path + competitionNumber + "Teams.json");
                    teamsInputDTO = JsonUtil.fromJsonFile(file, TeamsInputDTO.class);
                } catch (IOException e2) {
                    // In case that there is problem with file or it's data.
                    logger.error("There is a problem with the file. Standings update cannot be done.");

                    if (i == InputsConfig.competitionsId.length - 1) {
                        return "It was not possible to update competitions.";
                    } else {
                        logger.info("Teams update cannot be done.");
                        continue;
                    }

                }
            }

            logger.info("Saving the teams from competition with id: " + InputsConfig.competitionsId[i]);
            for (TeamDTO teamDTO : teamsInputDTO.getTeams()) {
                Team team = TeamMapper.INSTANCE.teamDtoToTeam(teamDTO);
                teamRepository.save(team);
            }

        } // end for loop


        return "Teams have been updated.";
    }


}
