package jacchm.footballapp.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class StandingsInputDTO {

    private Integer id;
    private CompetitionDTO competition;
    private SeasonDTO season;
    private List<StandingDTO> standings;

}
