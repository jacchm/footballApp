package jacchm.footballapp.service;

import jacchm.footballapp.mapping.dto.LeagueTablePositionDTO;

import java.util.List;

public interface ResultsService {

    void deleteAll();
    void updateAll();
    List<LeagueTablePositionDTO> getLeagueAllResults(Integer competitionId);
    List<LeagueTablePositionDTO> getLeagueResultsOfType(Integer competitionId, String type);

}
