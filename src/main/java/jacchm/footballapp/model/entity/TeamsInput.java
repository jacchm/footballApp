package jacchm.footballapp.model.entity;

import jacchm.footballapp.mapping.dto.CompetitionDTO;
import jacchm.footballapp.mapping.dto.SeasonDTO;
import jacchm.footballapp.mapping.dto.TeamDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class TeamsInput {

    @Id
    @GeneratedValue
    private int count;
    @OneToOne
    private CompetitionDTO competition;
    @OneToOne
    private SeasonDTO season;
    @OneToMany(cascade = CascadeType.ALL)
    private List<TeamDTO> teams;
}
