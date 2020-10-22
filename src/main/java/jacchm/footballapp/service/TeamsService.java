package jacchm.footballapp.service;

import jacchm.footballapp.mapping.dto.TeamDTO;

import java.util.List;

public interface TeamsService {

    boolean deleteAll();
    boolean updateAll();
    List<TeamDTO> getAllLeagueTeams(Integer competitionId);
    TeamDTO getTeam(Integer teamId);

}
