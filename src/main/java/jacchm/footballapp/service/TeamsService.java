package jacchm.footballapp.service;

import jacchm.footballapp.mapping.dto.TeamDTO;

import java.util.List;

public interface TeamsService {

    void deleteAll();
    void updateAll();
    List<TeamDTO> getAllLeagueTeams(Integer competitionId);
    TeamDTO getTeam(Integer teamId);

}
