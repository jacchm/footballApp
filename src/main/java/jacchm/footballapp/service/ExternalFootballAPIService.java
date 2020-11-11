package jacchm.footballapp.service;

import jacchm.footballapp.mapping.dto.CompetitionDTO;
import jacchm.footballapp.mapping.dto.LeagueTablePositionDTO;
import jacchm.footballapp.mapping.dto.TeamDTO;

import java.util.List;

public interface ExternalFootballAPIService {

    List<CompetitionDTO> getCompetitions();
    List<TeamDTO> getTeams(int competitionId);
    List<LeagueTablePositionDTO> getResults(int competitionId);

}
