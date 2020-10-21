package jacchm.footballapp.controller;

import jacchm.footballapp.mapping.dto.LeagueTablePositionDTO;
import jacchm.footballapp.service.ResultsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/results")
public class ResultsController {

    private final ResultsService resultsService;

    // TODO: method below should be only used by Admins
    @GetMapping("/updateAll")
    public boolean updateAll() {
       return resultsService.updateAll();
    }

    // TODO: method below should be only used by Admins
    @DeleteMapping("/deleteAll")
    public boolean deleteAllFromDataBase() {
        return resultsService.deleteAll();
    }

    @GetMapping("/get/{id}")
    public List<LeagueTablePositionDTO> getLeagueAllResults(@PathVariable("id") Integer id){
        return resultsService.getLeagueAllResults(id);
    }




}
