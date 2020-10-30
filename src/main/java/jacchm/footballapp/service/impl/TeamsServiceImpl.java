package jacchm.footballapp.service.impl;

import jacchm.footballapp.mapping.dto.TeamDTO;
import jacchm.footballapp.mapping.mapper.TeamMapper;
import jacchm.footballapp.repository.TeamRepository;
import jacchm.footballapp.service.ExternalFootballAPIService;
import jacchm.footballapp.service.TeamsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TeamsServiceImpl implements TeamsService {

    private final ExternalFootballAPIService footballDataOrgServiceImpl;
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    private final List<Integer> competitionIdList;

    public TeamsServiceImpl(ExternalFootballAPIService footballDataOrgServiceImpl,
                            TeamRepository teamRepository,
                            TeamMapper teamMapper,
                            @Value("${competitionsId}") List<Integer> competitionIdList) {
        this.footballDataOrgServiceImpl = footballDataOrgServiceImpl;
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

    @Transactional
    @Override
    public void updateAll() {
        for (Integer competitionId : competitionIdList) {
            teamRepository.saveAll(footballDataOrgServiceImpl.getTeams(competitionId).stream()
                    .map(teamMapper::mapToTeam)
                    .collect(Collectors.toList()));
        }
    }


}
