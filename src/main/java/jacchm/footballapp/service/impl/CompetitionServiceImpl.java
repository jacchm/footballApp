package jacchm.footballapp.service.impl;

import jacchm.footballapp.mapping.dto.CompetitionDTO;
import jacchm.footballapp.model.entity.Competition;
import jacchm.footballapp.mapping.mapper.CompetitionMapper;
import jacchm.footballapp.repository.CompetitionRepository;
import jacchm.footballapp.service.CompetitionService;
import jacchm.footballapp.service.ExternalFootballAPIService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompetitionServiceImpl implements CompetitionService {

    private final CompetitionRepository competitionRepository;
    private final ExternalFootballAPIService footballDataOrgServiceImpl;
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

    @Override
    public CompetitionDTO getById(Integer competitionId) {
        return competitionRepository.findById(competitionId)
                .map(competitionMapper::mapToCompetitionDTO)
                .orElse(new CompetitionDTO());
    }

    @Transactional
    @Scheduled(cron = "0 0 0 1 8 ?")
    @Override
    public void updateAll() {
        competitionRepository.
                saveAll(footballDataOrgServiceImpl.getCompetitions()
                        .stream()
                        .map(competitionMapper::mapToCompetition)
                        .collect(Collectors.toList()));
    }


}
