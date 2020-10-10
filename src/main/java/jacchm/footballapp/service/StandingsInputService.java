package jacchm.footballapp.service;

import com.fasterxml.jackson.databind.JsonNode;
import jacchm.footballapp.config.InputsConfig;
import jacchm.footballapp.mapping.dto.StandingsInputDTO;
import jacchm.footballapp.mapping.mapper.StandingsInputMapper;
import jacchm.footballapp.model.entity.StandingsInput;
import jacchm.footballapp.repository.StandingInputRepository;
import jacchm.footballapp.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StandingsInputService {

    private static final Logger logger = LoggerFactory.getLogger(StandingsInputService.class);

    private StandingInputRepository standingInputRepository;
    private ExternalFootballAPIService externalFootballAPIService;

    @Autowired
    public void setStandingInputRepository(StandingInputRepository standingInputRepository) {
        this.standingInputRepository = standingInputRepository;
    }

    @Autowired
    public void setExternalFootballAPIService(ExternalFootballAPIService externalFootballAPIService) {
        this.externalFootballAPIService = externalFootballAPIService;
    }

    public String deleteAll() {
        standingInputRepository.deleteAll();
        return "Delete has been completed.";
    }

    public List<StandingsInputDTO> getAllLeagueStandings(Integer competitionId) {
        List<StandingsInputDTO> allLeagueStandingsDTO = new ArrayList<>();

        // TODO: think about correct logic
        for (StandingsInput standingsInput : standingInputRepository.findByCompetition_Id(competitionId).
                orElse(new ArrayList<>())) {
            allLeagueStandingsDTO.add(StandingsInputMapper.INSTANCE.standingsInputToStandingsInputDto(standingsInput));
        }

        return allLeagueStandingsDTO;
    }

    // TODO: scheduled or done manually by admin
    public String updateAllStandings() {
        String jsonStandingsInput;
        StandingsInputDTO standingsInputDTO;
        JsonNode node;
        String path = "src\\main\\resources\\dataFromExternalApi\\";

        deleteAll(); // TODO: make logic find the existing inputs IDs and than override the old data for all elements in cascade

        for (int i = 0; i < InputsConfig.competitionsId.length; i++) {

            try {
                jsonStandingsInput = externalFootballAPIService.getStandings(InputsConfig.competitionsId[i]);
                node = JsonUtil.parse(jsonStandingsInput);
                standingsInputDTO = JsonUtil.fromJson(node, StandingsInputDTO.class);

                logger.info("Connection with external API has been established. Competitions have been achieved.");

            } catch (IOException e1) {
                // In case that external football API doesn't respond
                logger.error("External Football API hasn't responded. Program will try to achieve the data from file.");
                try {
                    String competitionNumber = Integer.toString(InputsConfig.competitionsId[i]);
                    File file = new File(path + competitionNumber + "Standings.json");
                    standingsInputDTO = JsonUtil.fromJsonFile(file, StandingsInputDTO.class);
                } catch (IOException e2) {
                    // In case that there is problem with file or it's data.
                    logger.error("There is a problem with the file. Standings update cannot be done.");
                    return "It was not possible to update competitions.";
                }
            }

            logger.info("Saving the standings.");
            StandingsInput standingsInput = StandingsInputMapper.INSTANCE.standingsInputDtoToStandingsInput(standingsInputDTO);

            // saving to repository
            standingInputRepository.save(standingsInput);

        } // end of for loop

        return "Standings have been updated.";
    }

}
