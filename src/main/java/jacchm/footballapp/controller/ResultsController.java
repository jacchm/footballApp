package jacchm.footballapp.controller;

import jacchm.footballapp.mapping.dto.LeagueTablePositionDTO;
import jacchm.footballapp.service.ResultsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/results")
public class ResultsController {

    private final ResultsService resultsService;

    // TODO: scheduled or done manually by admin
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update")
    public void update() {
        resultsService.updateAll();
    }

    // TODO: method below should be only used by Admins
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete")
    public void delete() {
        resultsService.deleteAll();
    }

    @GetMapping(value = "/league-results", params = "league_id")
    public List<LeagueTablePositionDTO> getLeagueAllResults(@RequestParam("league_id") Integer id) {
        return resultsService.getLeagueAllResults(id);
    }

    @GetMapping(value = "/league-results", params = {"league_id", "type"})
    public List<LeagueTablePositionDTO> getLeagueResultsOfType(@RequestParam("league_id") Integer id,
                                                               @RequestParam("type") String type) {
        return resultsService.getLeagueResultsOfType(id, type);
    }

}
