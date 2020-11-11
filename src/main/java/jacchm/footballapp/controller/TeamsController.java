package jacchm.footballapp.controller;

import jacchm.footballapp.mapping.dto.TeamDTO;
import jacchm.footballapp.service.TeamsService;
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
@RequestMapping("/teams")
public class TeamsController {

    private final TeamsService teamsService;

    // TODO: scheduled or done manually by admin
    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public void update() {
       teamsService.updateAll();
    }

    // TODO: method below should be only used by Admins
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping
    public void delete() {
        teamsService.deleteAll();
    }

    @GetMapping("/league-teams")
    public List<TeamDTO> getAllLeagueTeams(@RequestParam("league_id") Integer id){
        return teamsService.getAllLeagueTeams(id);
    }

    @GetMapping
    public TeamDTO getById(@RequestParam("team_id") Integer id){
        return teamsService.getTeam(id);
    }

}

