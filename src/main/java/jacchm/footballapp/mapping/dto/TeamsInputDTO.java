package jacchm.footballapp.mapping.dto;

import lombok.Data;

import java.util.List;

@Data
public class TeamsInputDTO {

    private int count;
    private CompetitionDTO competition;
    private SeasonDTO season;
    private List<TeamDTO> teams;


}
