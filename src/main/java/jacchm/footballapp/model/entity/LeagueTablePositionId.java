package jacchm.footballapp.model.entity;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Data
@Embeddable
public class LeagueTablePositionId implements Serializable {

    @OneToOne
    private Team team;
    private Integer competitionId;
    private String standingType;

}
