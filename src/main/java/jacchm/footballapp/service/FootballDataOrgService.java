package jacchm.footballapp.service;

import jacchm.footballapp.customexceptions.ExternalFootballApiConnectionException;
import jacchm.footballapp.mapping.dto.CompetitionDTO;
import jacchm.footballapp.mapping.dto.LeagueTablePositionDTO;
import jacchm.footballapp.mapping.dto.TeamDTO;

import java.util.List;

public interface FootballDataOrgService {

    List<CompetitionDTO> getCompetitions() throws ExternalFootballApiConnectionException;
    List<TeamDTO> getTeams(int competitionId) throws ExternalFootballApiConnectionException;
    List<LeagueTablePositionDTO> getResults(int competitionId) throws ExternalFootballApiConnectionException;

}
