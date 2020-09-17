package jacchm.footballapp.model.additional;

import jacchm.footballapp.model.dto.CompetitionDTO;
import jacchm.footballapp.model.dto.SeasonDTO;
import jacchm.footballapp.model.dto.StandingDTO;
import lombok.Data;

import java.util.List;

@Data
public class StandingsInput {

//    this is omitted because all standings are always necessary to get
//    private List<String> filters;
    CompetitionDTO competition;
    SeasonDTO season;
    List<StandingDTO> standings;

}
