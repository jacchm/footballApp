package jacchm.footballapp.service;

import jacchm.footballapp.customexceptions.ExternalFootballApiConnectionException;

public interface FootballDataOrgService {

    String getCompetitions() throws ExternalFootballApiConnectionException;
    String getTeams(int competitionId) throws ExternalFootballApiConnectionException;
    String getResults(int competitionId) throws ExternalFootballApiConnectionException;

}
