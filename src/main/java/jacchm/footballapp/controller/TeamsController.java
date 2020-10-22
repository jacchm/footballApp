package jacchm.footballapp.controller;

import jacchm.footballapp.mapping.dto.TeamDTO;
import jacchm.footballapp.service.TeamsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/teams")
public class TeamsController {

    private final TeamsService teamsService;

    // TODO: scheduled or done manually by admin
    @GetMapping("/updateAll")
    public boolean updateAll() {
       return teamsService.updateAll();
    }

    // TODO: method below should be only used by Admins
    @DeleteMapping("/deleteAll")
    public boolean deleteAll() {
        return teamsService.deleteAll();
    }

    @GetMapping("/getLeagueTeams/{id}")
    public List<TeamDTO> getAllLeagueTeams(@PathVariable("id") Integer id){
        return teamsService.getAllLeagueTeams(id);
    }

    @GetMapping("/getTeam/{id}")
    public TeamDTO getTeam(@PathVariable("id") Integer id){
        return teamsService.getTeam(id);
    }

}
