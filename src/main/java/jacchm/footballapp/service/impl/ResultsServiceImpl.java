package jacchm.footballapp.service.impl;

import jacchm.footballapp.mapping.dto.LeagueTablePositionDTO;
import jacchm.footballapp.mapping.mapper.LeagueTablePositionMapper;
import jacchm.footballapp.repository.LeagueTablePositionRepository;
import jacchm.footballapp.service.ExternalFootballAPIService;
import jacchm.footballapp.service.ResultsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ResultsServiceImpl implements ResultsService {

    private final ExternalFootballAPIService footballDataOrgServiceImpl;
    private final LeagueTablePositionRepository leagueTablePositionRepository;
    private final LeagueTablePositionMapper leagueTablePositionMapper;
    private final List<Integer> competitionIdList;

    public ResultsServiceImpl(ExternalFootballAPIService footballDataOrgServiceImpl,
                              LeagueTablePositionRepository leagueTablePositionRepository,
                              LeagueTablePositionMapper leagueTablePositionMapper,
                              @Value("${competitionsId}") List<Integer> competitionIdList) {
        this.footballDataOrgServiceImpl = footballDataOrgServiceImpl;
        this.leagueTablePositionRepository = leagueTablePositionRepository;
        this.leagueTablePositionMapper = leagueTablePositionMapper;
        this.competitionIdList = competitionIdList;
    }

    @Override
    public void deleteAll() {
        leagueTablePositionRepository.deleteAll();
    }

    @Override
    public List<LeagueTablePositionDTO> getLeagueAllResults(Integer competitionId) {
        return leagueTablePositionRepository.
                findByCompetitionId(competitionId)
                .stream()
                .map(leagueTablePositionMapper::mapToLeagueTablePositionDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LeagueTablePositionDTO> getLeagueResultsOfType(Integer competitionId, String type) {
        return leagueTablePositionRepository
                .findByCompetitionIdAndType(competitionId, type)
                .stream()
                .map(leagueTablePositionMapper::mapToLeagueTablePositionDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void updateAll() {
        for (Integer competitionId : competitionIdList) {
            leagueTablePositionRepository.
                    saveAll(footballDataOrgServiceImpl.getResults(competitionId)
                            .stream()
                            .map(leagueTablePositionMapper::mapToLeagueTablePosition)
                            .collect(Collectors.toList()));
        }
    }

}
