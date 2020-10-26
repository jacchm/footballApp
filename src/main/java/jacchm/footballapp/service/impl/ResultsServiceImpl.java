package jacchm.footballapp.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import jacchm.footballapp.customexceptions.ExternalFootballApiConnectionException;
import jacchm.footballapp.mapping.dto.LeagueTablePositionDTO;
import jacchm.footballapp.mapping.inputs.SingleStandingInput;
import jacchm.footballapp.mapping.inputs.StandingsListInput;
import jacchm.footballapp.mapping.mapper.LeagueTablePositionMapper;
import jacchm.footballapp.model.entity.LeagueTablePosition;
import jacchm.footballapp.repository.LeagueTablePositionRepository;
import jacchm.footballapp.service.FootballDataOrgService;
import jacchm.footballapp.service.ResultsService;
import jacchm.footballapp.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ResultsServiceImpl implements ResultsService {

    private final FootballDataOrgService footballDataOrgService;
    private final LeagueTablePositionRepository leagueTablePositionRepository;
    private final LeagueTablePositionMapper leagueTablePositionMapper;

    @Value("${COMPETITIONS_ID}")
    private final List<Integer> competitionIdList;

    @Override
    public boolean deleteAll() {
        leagueTablePositionRepository.deleteAll();
        return true;
    }

    @Override
    public List<LeagueTablePositionDTO> getLeagueAllResults(Integer competitionId) {

        List<LeagueTablePositionDTO> leagueResults = new ArrayList<>();
        List<LeagueTablePosition> allLeagueResults = leagueTablePositionRepository.findByLeagueTablePositionId_CompetitionId(competitionId);

        for (LeagueTablePosition leagueTablePosition: allLeagueResults) {
            leagueResults.add(leagueTablePositionMapper.mapToLeagueTablePositionDTO(leagueTablePosition));
        }

        return leagueResults;
    }

    @Override
    public List<LeagueTablePositionDTO> getLeagueResultsOfType(Integer competitionId, String type) {

        List<LeagueTablePositionDTO> leagueResults = new ArrayList<>();
        List<LeagueTablePosition> allLeagueResults = leagueTablePositionRepository
                .findByLeagueTablePositionId_CompetitionIdAndLeagueTablePositionId_StandingTypeOrderByPosition(competitionId, type);

        for (LeagueTablePosition leagueTablePosition: allLeagueResults) {
            leagueResults.add(leagueTablePositionMapper.mapToLeagueTablePositionDTO(leagueTablePosition));
        }

        return leagueResults;
    }

    @Override
    public boolean updateAll() {

        for (int i = 0; i < competitionIdList.size(); i++) {

            try {
                JsonNode node = JsonUtil.parse(footballDataOrgService.getResults(competitionIdList.get(i)));
                StandingsListInput standingsListInput = JsonUtil.fromJson(node, StandingsListInput.class);

                saveInDataBase(standingsListInput);
            } catch (JsonProcessingException e) {
                log.error("Error has been encountered during JSON parsing.", e);
                return false;
            } catch (ExternalFootballApiConnectionException e) {
                log.error("Error has been encountered when connecting to external football API.", e);
                return false;
            }

        } // end of for loop

        return true;
    }

    private void saveInDataBase(StandingsListInput standingsListInput) {
        Integer competitionId = standingsListInput.getCompetition().getId();

        for (SingleStandingInput singleStandingInput : standingsListInput.getStandings()) {
            String type = singleStandingInput.getType();

            for (LeagueTablePositionDTO leagueTablePositionDTO: singleStandingInput.getTable()) {
                leagueTablePositionDTO.setType(type);
                leagueTablePositionDTO.setCompetitionId(competitionId);

                leagueTablePositionRepository.save(
                        leagueTablePositionMapper.mapToLeagueTablePosition(leagueTablePositionDTO));
            }

        } // end of for loop

        log.info("Results update for competition ID: " + competitionId + " completed.");
    }

}
