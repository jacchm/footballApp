package jacchm.footballapp.servlet;

import com.fasterxml.jackson.databind.JsonNode;
import jacchm.footballapp.model.dto.CompetitionDTO;
import jacchm.footballapp.model.mapper.CompetitionMapper;
import jacchm.footballapp.service.ExternalFootballAPI;
import jacchm.footballapp.util.JsonUtil;
import jacchm.footballapp.model.entity.Competition;
import jacchm.footballapp.model.additional.CompetitionsInput;
import jacchm.footballapp.repository.CompetitionRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/competitions")
public class CompetitionServlet {

    CompetitionRepository competitionRepository;
    ExternalFootballAPI externalFootballAPI;

    private final Logger logger = LoggerFactory.getLogger(CompetitionServlet.class);

    @GetMapping("/updateAllFromFile")
    public String updateCompetitionDataBaseFromFile() {
        File file = new File("D:\\JavaProjects\\footballapp\\src\\main\\" +
                "resources\\dataFromExternalApi\\AllCompetitions.json");
        CompetitionsInput competitionsInputDTO;
        try {
            competitionsInputDTO = JsonUtil.fromJsonFile(file, CompetitionsInput.class);

//            System.out.println("CompetitionsInputDTO " + competitionsInputDTO.getCount() + "   " + competitionsInputDTO.getId());
//            System.out.println("CompetitionsInputDTO is list empty: " + competitionsInputDTO.getCompetitions().isEmpty());

            for (CompetitionDTO competitionDTO : competitionsInputDTO.getCompetitions()) {
                Competition competition = CompetitionMapper.INSTANCE.competitionDtoToCompetition(competitionDTO);
                competitionRepository.save(competition);
            }

            logger.info("Competitions have been successfully added to the database");
            return "DONE";

        } catch (IOException e) {
            logger.error("Error has been encountered. ");
            return "Something went wrong";
        }
    }

    @GetMapping("/updateAll")
    public String updateCompetitionDataBase() {

        String jsonCompetitionInput;
        CompetitionsInput competitionsInputDTO;
        JsonNode node;

        try {
            jsonCompetitionInput = externalFootballAPI.getCompetitions();
            node = JsonUtil.parse(jsonCompetitionInput);
            competitionsInputDTO = JsonUtil.fromJson(node, CompetitionsInput.class);

            for (CompetitionDTO competitionDTO : competitionsInputDTO.getCompetitions()) {
                Competition competition = CompetitionMapper.INSTANCE.competitionDtoToCompetition(competitionDTO);
                competitionRepository.save(competition);
            }

            logger.info("Competitions have been successfully added to the database");
            return "DONE";

        } catch (IOException e) {
            logger.error("Error during json handling has been encountered.");
            return "Json has not been handled successfully";
        }
    }

    @DeleteMapping("/deleteAll")
    public String deleteAllCompetitionsFromDataBase() {
        competitionRepository.deleteAll();

        return "Delete completed";
    }



}

