package jacchm.footballapp.controller;

import jacchm.footballapp.mapping.dto.CompetitionDTO;
import jacchm.footballapp.service.CompetitionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/competitions")
public class CompetitionController {

    private final CompetitionService competitionService;

    // TODO: scheduled or done manually by admin
    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public void update() {
        competitionService.updateAll();
    }

    // TODO: method below should be only used by Admins
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping
    public void delete() {
        competitionService.deleteAll();
    }

    @GetMapping
    public List<CompetitionDTO> getAll() {
        return competitionService.getAll();
    }

    @GetMapping
    public CompetitionDTO getById(@RequestParam("competition_id") Integer id) {
        return competitionService.getById(id);
    }

}

