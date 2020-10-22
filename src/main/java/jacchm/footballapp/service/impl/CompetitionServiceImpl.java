package jacchm.footballapp.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import jacchm.footballapp.customexceptions.ExternalFootballApiConnectionException;
import jacchm.footballapp.mapping.inputs.CompetitionsInput;
import jacchm.footballapp.mapping.dto.CompetitionDTO;
import jacchm.footballapp.model.entity.Competition;
import jacchm.footballapp.mapping.mapper.CompetitionMapper;
import jacchm.footballapp.repository.CompetitionRepository;
import jacchm.footballapp.service.CompetitionService;
import jacchm.footballapp.service.FootballDataOrgService;
import jacchm.footballapp.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final FootballDataOrgService footballDataOrgService;
    private final CompetitionMapper competitionMapper;

    @Override
    public boolean deleteAll() {
        competitionRepository.deleteAll();
        return true;
    }

    @Override
    public List<CompetitionDTO> getAll() {
        List<CompetitionDTO> allCompetitionsDTO = new ArrayList<>();
        for (Competition competition : competitionRepository.findAll()) {
            allCompetitionsDTO.add(competitionMapper.competitionToCompetitionDTO(competition));
        }
        return allCompetitionsDTO;
    }

    public CompetitionDTO getById(Integer id) {
        return competitionMapper.competitionToCompetitionDTO(
                competitionRepository.findById(id).orElse(new Competition()));
    }

    @Override
    public boolean updateAll() {

        try {
            JsonNode node = JsonUtil.parse(footballDataOrgService.getCompetitions());
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
