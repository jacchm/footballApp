package jacchm.footballapp.controller;

import jacchm.footballapp.mapping.dto.StandingsInputDTO;
import jacchm.footballapp.repository.StandingInputRepository;
import jacchm.footballapp.service.ExternalFootballAPIService;
import jacchm.footballapp.service.StandingsInputService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/standings")
public class StandingsController {

    ExternalFootballAPIService externalFootballAPIService;
    StandingInputRepository standingInputRepository;
    StandingsInputService standingsInputService;

    @GetMapping("/updateAll")
    public String updateStandingsDataBaseFromFile() {
       return standingsInputService.updateAllStandings();
    }

    @GetMapping("/get/{id}")
    public List<StandingsInputDTO> getAllLeagueStandings(@PathVariable("id") Integer id){
        return standingsInputService.getAllLeagueStandings(id);
    }

    @DeleteMapping("/deleteAll")
    public String deleteAllFromDataBase() {
        return standingsInputService.deleteAll();
    }

}
