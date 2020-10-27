package jacchm.footballapp.service.impl;

import jacchm.footballapp.customexceptions.ExternalFootballApiConnectionException;
import jacchm.footballapp.mapping.dto.TeamDTO;
import jacchm.footballapp.mapping.mapper.TeamMapper;
import jacchm.footballapp.repository.TeamRepository;
import jacchm.footballapp.service.FootballDataOrgService;
import jacchm.footballapp.service.TeamsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TeamsServiceImpl implements TeamsService {

    private final FootballDataOrgService footballDataOrgService;
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    private final List<Integer> competitionIdList;

    public TeamsServiceImpl(FootballDataOrgService footballDataOrgService,
                            TeamRepository teamRepository,
                            TeamMapper teamMapper,
                            @Value("${COMPETITIONS_ID}") List<Integer> competitionIdList) {
        this.footballDataOrgService = footballDataOrgService;
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
        this.competitionIdList = competitionIdList;
    }

    @Override
    public void deleteAll() {
        teamRepository.deleteAll();
    }

    @Override
    public List<TeamDTO> getAllLeagueTeams(Integer competitionId) {
        return teamRepository.findByCompetitionId(competitionId)
                .stream()
                .map(teamMapper::mapToTeamDto)
                .collect(Collectors.toList());
    }

    @Override
    public TeamDTO getTeam(Integer teamId) {
        return teamRepository.findById(teamId).map(teamMapper::mapToTeamDto).orElse(new TeamDTO());
    }

    @Override
    public void updateAll() {
        for (int i = 0; i < competitionIdList.size(); i++) {
            try {
                saveListInDataBase(footballDataOrgService.getTeams(competitionIdList.get(i)));
            } catch (ExternalFootballApiConnectionException e) {
                log.info("Connection with football data api has not been established." + e);
            }
        }
    }

    private void saveListInDataBase(List<TeamDTO> teamDTOList) {
        if (teamDTOList != null) {
            teamRepository.
                    saveAll(teamDTOList
                            .stream()
                            .map(teamMapper::mapToTeam)
                            .collect(Collectors.toList()));
            log.info("Teams update for competition ID: " + teamDTOList.get(0).getCompetitionId() + " completed.");
        }
    }

}
