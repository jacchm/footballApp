package jacchm.footballapp.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class TableInput implements Serializable {

    // think about composite key not the generated Integer id;
    @Id
    @GeneratedValue
    private Integer id;
    private Integer position;
    @OneToOne
    private Team team;
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
