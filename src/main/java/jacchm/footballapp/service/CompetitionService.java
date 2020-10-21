package jacchm.footballapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import jacchm.footballapp.customexceptions.ExternalFootballApiConnectionException;
import jacchm.footballapp.mapping.inputs.CompetitionsInput;
import jacchm.footballapp.mapping.dto.CompetitionDTO;
import jacchm.footballapp.model.entity.Competition;
import jacchm.footballapp.mapping.mapper.CompetitionMapper;
import jacchm.footballapp.repository.CompetitionRepository;
import jacchm.footballapp.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final ExternalFootballAPIService externalFootballAPIService;
    private final CompetitionMapper competitionMapper;

    public boolean deleteAll() {
        competitionRepository.deleteAll();
        return true;
    }

    public List<CompetitionDTO> getAll() {
        List<CompetitionDTO> allCompetitionsDTO = new ArrayList<>();
        for (Competition competition : competitionRepository.findAll()) {
            allCompetitionsDTO.add(competitionMapper.competitionToCompetitionDTO(competition));
        }
        return allCompetitionsDTO;
    }

    public CompetitionDTO getById(Integer id) {
        Competition toBeReturned = competitionRepository.findById(id).orElse(new Competition());
        return competitionMapper.competitionToCompetitionDTO(toBeReturned);
    }

    // TODO: scheduled or done manually by admin
    public boolean updateAll() {

        try {
            JsonNode node = JsonUtil.parse(externalFootballAPIService.getCompetitions());
            CompetitionsInput competitionsInputDTO = JsonUtil.fromJson(node, CompetitionsInput.class);

            saveInDataBase(competitionsInputDTO);

        } catch (JsonProcessingException e) {
            log.error("Error has been encountered during JSON parsing.", e);
        } catch (ExternalFootballApiConnectionException e) {
            log.error("Error has been encountered when connecting to external football API.", e);
        }

        return true;
    }

    private void saveInDataBase(CompetitionsInput competitionsInputDTO){
        for (CompetitionDTO competitionDTO : competitionsInputDTO.getCompetitions()) {
            Competition competition = competitionMapper.competitionDtoToCompetition(competitionDTO);
            competitionRepository.save(competition);
        }
        log.info("Competitions update completed.");
    }

}
