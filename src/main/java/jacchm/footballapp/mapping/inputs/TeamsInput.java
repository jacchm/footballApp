package jacchm.footballapp.mapping.inputs;

import jacchm.footballapp.mapping.dto.CompetitionDTO;
import jacchm.footballapp.mapping.dto.SeasonDTO;
import jacchm.footballapp.mapping.dto.TeamDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TeamsInput {

    private int count;
    private CompetitionDTO competition;
    private SeasonDTO season;
    private List<TeamDTO> teams = new ArrayList<>();

}
