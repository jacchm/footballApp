package jacchm.footballapp.servlet;

import jacchm.footballapp.mapping.dto.CompetitionDTO;
import jacchm.footballapp.service.CompetitionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/competitions")
public class CompetitionController {

    private CompetitionService competitionService;

    // method below should be only used by Admins
    @GetMapping("/updateAll")
    public String updateFromFile() {
        return competitionService.updateCompetitions();
    }

    // method below should be only used by Admins
    @DeleteMapping("/deleteAll")
    public String deleteAllFromDataBase() {
        return competitionService.deleteAll();
    }


    @GetMapping("/get/all")
    public List<CompetitionDTO> getAll() {
        return competitionService.getAll();
    }

    @GetMapping("/get/{id}")
    public CompetitionDTO getById(@PathVariable("id") Integer id) {
        return competitionService.getById(id);
    }



}

