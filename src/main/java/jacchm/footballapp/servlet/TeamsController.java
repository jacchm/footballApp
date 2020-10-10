package jacchm.footballapp.servlet;

import jacchm.footballapp.mapping.dto.TeamsInputDTO;
import jacchm.footballapp.mapping.dto.TeamDTO;
import jacchm.footballapp.model.entity.Team;
import jacchm.footballapp.mapping.mapper.TeamMapper;
import jacchm.footballapp.repository.TeamRepository;
import jacchm.footballapp.util.JsonUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/teams")
public class TeamsController {

    TeamRepository teamRepository;

    private final Logger logger = LoggerFactory.getLogger(TeamsController.class);

    @GetMapping("/updateAll")
    public String updateTeamDataBaseFromFile() {
        File file = new File("D:\\JavaProjects\\footballapp\\src\\main\\" +
                "resources\\dataFromExternalApi\\2021Teams.json");
        TeamsInputDTO teamsInputDTO;
        try {
            teamsInputDTO = JsonUtil.fromJsonFile(file, TeamsInputDTO.class);

//            System.out.println("CompetitionsInputDTO " + teamsInput.getCount() + "   " + teamsInput.get());
//            System.out.println("CompetitionsInputDTO is list empty: " + teamsInput.getTeams().isEmpty());

            for (TeamDTO teamDTO : teamsInputDTO.getTeams()) {
                Team team = TeamMapper.INSTANCE.teamDtoToTeam(teamDTO);
                teamRepository.save(team);
            }

            logger.info("Teams have been successfully added to the database");
            return "DONE";

        } catch (IOException e) {
            logger.error("Error has been encountered. ");
            return "Something went wrong";
        }
    }



}

