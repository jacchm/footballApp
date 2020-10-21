package jacchm.footballapp.mapping.inputs;

import jacchm.footballapp.mapping.dto.CompetitionDTO;
import jacchm.footballapp.mapping.dto.SeasonDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StandingsListInput {

    private Integer id;
    private CompetitionDTO competition;
    private SeasonDTO season;
    private List<SingleStandingInput> standings = new ArrayList<>();

}
