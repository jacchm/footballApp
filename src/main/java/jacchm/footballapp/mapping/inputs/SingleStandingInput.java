package jacchm.footballapp.mapping.inputs;

import jacchm.footballapp.mapping.dto.LeagueTablePositionDTO;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SingleStandingInput {

    private Integer competitionId;
    private String stage;
    private String type;
    private String group;
    private List<LeagueTablePositionDTO> table = new ArrayList<>();

}

