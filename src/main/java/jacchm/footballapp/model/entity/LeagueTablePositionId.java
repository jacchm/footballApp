package jacchm.footballapp.model.entity;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Embeddable
public class LeagueTablePositionId implements Serializable {

    @ManyToOne
    private Team team;
    private Integer competitionId;
    private String standingType;

}
