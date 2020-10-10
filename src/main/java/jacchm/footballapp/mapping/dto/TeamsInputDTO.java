package jacchm.footballapp.mapping.dto;

import jacchm.footballapp.mapping.dto.CompetitionDTO;
import jacchm.footballapp.mapping.dto.SeasonDTO;
import jacchm.footballapp.mapping.dto.TeamDTO;
import lombok.Data;

import java.util.List;

@Data
public class TeamsInputDTO {

    private int count;
    private CompetitionDTO competition;
    private SeasonDTO season;
    private List<TeamDTO> teams;
}
