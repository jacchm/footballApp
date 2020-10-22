package jacchm.footballapp.service;

import jacchm.footballapp.mapping.dto.LeagueTablePositionDTO;

import java.util.List;

public interface ResultsService {

    boolean deleteAll();
    boolean updateAll();
    List<LeagueTablePositionDTO> getLeagueAllResults(Integer competitionId);

}
