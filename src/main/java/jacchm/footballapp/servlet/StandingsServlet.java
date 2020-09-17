package jacchm.footballapp.servlet;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@AllArgsConstructor
@RestController
@RequestMapping("/standings")
public class StandingsServlet {

    ExternalFootballAPI externalFootballAPI;
    StandingRepository standingRepository;

    private final Logger logger = LoggerFactory.getLogger(CompetitionServlet.class);

    @GetMapping("/updateAllFromFile")
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

    @GetMapping("/deleteAll")
    public String deleteAllStandingsFromDataBase() {
        standingRepository.deleteAll();

        return "Delete completed";
    }

}
