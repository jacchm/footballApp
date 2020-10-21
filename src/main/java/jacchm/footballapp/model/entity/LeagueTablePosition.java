package jacchm.footballapp.model.entity;

import lombok.Data;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Data
@Entity
public class LeagueTablePosition implements Serializable {

    @EmbeddedId
    private LeagueTablePositionId leagueTablePositionId;
    private Integer position;
    private Integer playedGames;
    private String form;
    private Integer won;
    private Integer draw;
    private Integer lost;
    private Integer points;
    private Integer goalsFor;
    private Integer goalsAgainst;
    private Integer goalDifference;

}
