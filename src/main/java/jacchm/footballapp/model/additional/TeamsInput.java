package jacchm.footballapp.model.additional;

import jacchm.footballapp.model.dto.CompetitionDTO;
import jacchm.footballapp.model.dto.SeasonDTO;
import jacchm.footballapp.model.dto.TeamDTO;
import lombok.Data;

import java.util.List;

@Data
public class TeamsInput {

    private int count;
    private CompetitionDTO competition;
    private SeasonDTO season;
    private List<TeamDTO> teams;
}
