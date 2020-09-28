package jacchm.footballapp.servlet;

import com.fasterxml.jackson.databind.JsonNode;
import jacchm.footballapp.model.additional.StandingsInput;
import jacchm.footballapp.model.dto.StandingDTO;
import jacchm.footballapp.model.entity.Standing;
import jacchm.footballapp.model.mapper.StandingMapper;
import jacchm.footballapp.repository.StandingRepository;
import jacchm.footballapp.service.ExternalFootballAPI;
import jacchm.footballapp.util.JsonUtil;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/standings")
public class StandingsServlet {

    ExternalFootballAPI externalFootballAPI;
    StandingRepository standingRepository;

    private final Logger logger = LoggerFactory.getLogger(CompetitionServlet.class);

    @GetMapping("/updateFromFile")
    public String updateStandingsDataBaseFromFile() {
        File file = new File("D:\\JavaProjects\\footballapp\\src\\main\\resources\\dataFromExternalApi" +
                "\\EnglishSeasonFromAPI.json");
        StandingsInput standingsInputDTO;
        try {
            standingsInputDTO = JsonUtil.fromJsonFile(file, StandingsInput.class);

            System.out.println(standingsInputDTO.getStandings().toString());


            for (StandingDTO standingDTO : standingsInputDTO.getStandings()) {
                Standing standing = StandingMapper.INSTANCE.standingDtoToStanding(standingDTO);
                standingRepository.save(standing);
            }

            logger.info("Standings have been successfully added to the database");
            return "Standings have been successfully added to the database";

        } catch (IOException e) {
            logger.error("Error has been encountered. ");
            return "Something went wrong";
        }
    }

    @GetMapping("/update")
    @ResponseBody
    public String updateStandingsDataBase(@RequestParam String id) {

        String jsonCompetitionInput;
        StandingsInput standingsInputDTO;
        JsonNode node;

        try {
            jsonCompetitionInput = externalFootballAPI.getStandings(id);
            node = JsonUtil.parse(jsonCompetitionInput);
            standingsInputDTO = JsonUtil.fromJson(node, StandingsInput.class);

            System.out.println(standingsInputDTO.getStandings().toString());

            for (StandingDTO standingDTO : standingsInputDTO.getStandings()) {
                Standing standing = StandingMapper.INSTANCE.standingDtoToStanding(standingDTO);
                standingRepository.save(standing);
            }

            logger.info("Standings have been successfully added to the database");
            return "Standings have been successfully added to the database";

        } catch (IOException e) {
            logger.error("Error has been encountered. ");
            return "Something went wrong";
        }
    }

    @GetMapping("/testmethod")
    @ResponseBody
    public String testMethod(@RequestParam String id) {

        return "Your request param was " + id;
    }


    @DeleteMapping("/deleteAll")
    public String deleteAllStandingsFromDataBase() {
        standingRepository.deleteAll();

        return "Delete completed";
    }

}
