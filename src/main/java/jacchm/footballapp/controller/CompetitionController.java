package jacchm.footballapp.controller;

import jacchm.footballapp.mapping.dto.CompetitionDTO;
import jacchm.footballapp.service.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/competitions")
public class CompetitionController {

    private final CompetitionService competitionService;

    // TODO: scheduled or done manually by admin
    @GetMapping("/updateAll")
    public boolean updateAll() {
        return competitionService.updateAll();
    }

    // TODO: method below should be only used by Admins
    @DeleteMapping("/deleteAll")
    public boolean deleteAllFromDataBase() {
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

