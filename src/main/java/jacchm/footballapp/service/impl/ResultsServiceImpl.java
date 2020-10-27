package jacchm.footballapp.service.impl;

import jacchm.footballapp.customexceptions.ExternalFootballApiConnectionException;
import jacchm.footballapp.mapping.dto.LeagueTablePositionDTO;
import jacchm.footballapp.mapping.mapper.LeagueTablePositionMapper;
import jacchm.footballapp.repository.LeagueTablePositionRepository;
import jacchm.footballapp.service.FootballDataOrgService;
import jacchm.footballapp.service.ResultsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ResultsServiceImpl implements ResultsService {

    private final FootballDataOrgService footballDataOrgService;
    private final LeagueTablePositionRepository leagueTablePositionRepository;
    private final LeagueTablePositionMapper leagueTablePositionMapper;
    private final List<Integer> competitionIdList;

    public ResultsServiceImpl(FootballDataOrgService footballDataOrgService,
                              LeagueTablePositionRepository leagueTablePositionRepository,
                              LeagueTablePositionMapper leagueTablePositionMapper,
                              @Value("${COMPETITIONS_ID}") List<Integer> competitionIdList) {
        this.footballDataOrgService = footballDataOrgService;
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
                findByLeagueTablePositionId_CompetitionId(competitionId)
                .stream()
                .map(leagueTablePositionMapper::mapToLeagueTablePositionDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<LeagueTablePositionDTO> getLeagueResultsOfType(Integer competitionId, String type) {
        return leagueTablePositionRepository
                .findByLeagueTablePositionId_CompetitionIdAndLeagueTablePositionId_StandingTypeOrderByPosition(competitionId, type)
                .stream()
                .map(leagueTablePositionMapper::mapToLeagueTablePositionDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void updateAll() {
        for (int i = 0; i < competitionIdList.size(); i++) {
            try {
                saveListInDataBase(footballDataOrgService.getResults(competitionIdList.get(i)));
            } catch (ExternalFootballApiConnectionException e) {
                log.info("Connection with football data api has not been established." + e);
            }
        }
    }

    private void saveListInDataBase(List<LeagueTablePositionDTO> leagueTablePositionDTOList) {
        if (leagueTablePositionDTOList != null) {
            leagueTablePositionRepository.
                    saveAll(leagueTablePositionDTOList
                            .stream()
                            .map(leagueTablePositionMapper::mapToLeagueTablePosition)
                            .collect(Collectors.toList()));
            log.info("Teams update for competition ID: " + leagueTablePositionDTOList.get(0).getCompetitionId() + " completed.");
        }
    }

}
