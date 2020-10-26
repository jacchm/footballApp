package jacchm.footballapp.controller;

import jacchm.footballapp.mapping.dto.TeamDTO;
import jacchm.footballapp.service.TeamsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/teams")
public class TeamsController {

    private final TeamsService teamsService;

    // TODO: scheduled or done manually by admin
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/update")
    public void updateAll() {
       teamsService.updateAll();
    }

    // TODO: method below should be only used by Admins
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/delete")
    public void deleteAll() {
        teamsService.deleteAll();
    }

    @GetMapping("/league-teams")
    public List<TeamDTO> getAllLeagueTeams(@RequestParam("league_id") Integer id){
        return teamsService.getAllLeagueTeams(id);
    }

    @GetMapping
    public TeamDTO getTeam(@PathVariable("team_id") Integer id){
        return teamsService.getTeam(id);
    }

}

