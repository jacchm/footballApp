package jacchm.footballapp.model.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Embeddable
public class LeagueTablePositionId implements Serializable {

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Team team;
    private Integer competitionId;
    private String standingType;

}
