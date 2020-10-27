package jacchm.footballapp.service.impl;

import jacchm.footballapp.customexceptions.ExternalFootballApiConnectionException;
import jacchm.footballapp.mapping.dto.CompetitionDTO;
import jacchm.footballapp.model.entity.Competition;
import jacchm.footballapp.mapping.mapper.CompetitionMapper;
import jacchm.footballapp.repository.CompetitionRepository;
import jacchm.footballapp.service.CompetitionService;
import jacchm.footballapp.service.FootballDataOrgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final FootballDataOrgService footballDataOrgService;
    private final CompetitionMapper competitionMapper;

    @Override
    public void deleteAll() {
        competitionRepository.deleteAll();
    }

    @Override
    public List<CompetitionDTO> getAll() {
        return ((List<Competition>) competitionRepository.findAll()).stream()
                .map(competitionMapper::mapToCompetitionDTO).collect(Collectors.toList());
    }

    public CompetitionDTO getById(Integer id) {
        return competitionRepository.findById(id)
                .map(competitionMapper::mapToCompetitionDTO)
                .orElse(new CompetitionDTO());
    }

    @Override
    public void updateAll() {
        try {
            saveListInDataBase(footballDataOrgService.getCompetitions());
        } catch (ExternalFootballApiConnectionException e) {
            log.info("Connection with football data api has not been established." + e);
        }
    }

    private void saveListInDataBase(List<CompetitionDTO> competitionDTOList) {
        if (competitionDTOList != null) {
            competitionRepository.
                    saveAll(competitionDTOList
                            .stream()
                            .map(competitionMapper::mapToCompetition)
                            .collect(Collectors.toList()));
        }
    }

}
