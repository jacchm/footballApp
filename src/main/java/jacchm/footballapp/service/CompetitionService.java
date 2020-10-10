package jacchm.footballapp.service;

import com.fasterxml.jackson.databind.JsonNode;
import jacchm.footballapp.mapping.inputs.CompetitionsInput;
import jacchm.footballapp.mapping.dto.CompetitionDTO;
import jacchm.footballapp.model.entity.Competition;
import jacchm.footballapp.mapping.mapper.CompetitionMapper;
import jacchm.footballapp.repository.CompetitionRepository;
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
public class CompetitionService {

    private static final Logger logger = LoggerFactory.getLogger(CompetitionService.class);
    private File file = new File("src\\main\\" +
            "resources\\dataFromExternalApi\\AllCompetitions.json");

    private CompetitionRepository competitionRepository;
    private ExternalFootballAPIService externalFootballAPIService;

    @Autowired
    public void setCompetitionRepository(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    @Autowired
    public void setExternalFootballAPIService(ExternalFootballAPIService externalFootballAPIService) {
        this.externalFootballAPIService = externalFootballAPIService;
    }


    public String deleteAll() {
        competitionRepository.deleteAll();
        return "Delete has been completed.";
    }

    public List<CompetitionDTO> getAll() {
        List<CompetitionDTO> allCompetitionsDTO = new ArrayList<>();
        for (Competition competition : competitionRepository.findAll()) {
            allCompetitionsDTO.add(CompetitionMapper.INSTANCE.competitionToCompetitionDTO(competition));
        }

        return allCompetitionsDTO;
    }

    public CompetitionDTO getById(Integer id) {
        Competition toBeReturned = competitionRepository.findById(id).orElse(new Competition());
        return CompetitionMapper.INSTANCE.competitionToCompetitionDTO(toBeReturned);
    }


    // TODO: scheduled or done manually by admin
    public String updateCompetitions() {
        String jsonCompetitionInput;
        CompetitionsInput competitionsInputDTO;
        JsonNode node;

        try {
            jsonCompetitionInput = externalFootballAPIService.getCompetitions();
            node = JsonUtil.parse(jsonCompetitionInput);
            competitionsInputDTO = JsonUtil.fromJson(node, CompetitionsInput.class);

            logger.info("Connection with external API has been established. Competitions have been achieved.");

        } catch (IOException e1) {
            // In case that external football API doesn't respond
            logger.error("External Football API hasn't responded. Program will try to achieve the data from file.");
            try {
                competitionsInputDTO = JsonUtil.fromJsonFile(file, CompetitionsInput.class);
            } catch (IOException e2) {
                // In case that there is problem with file or it's data.
                logger.error("There is a problem with the file. Competitions update cannot be done.");
                return "It was not possible to update competitions.";
            }
        }

        logger.info("Saving the competitions.");
        for (CompetitionDTO competitionDTO : competitionsInputDTO.getCompetitions()) {
            Competition competition = CompetitionMapper.INSTANCE.competitionDtoToCompetition(competitionDTO);
            competitionRepository.save(competition);
        }

        return "Competitions have been updated";
    }

}
