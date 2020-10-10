package jacchm.footballapp.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class StandingsInput implements Serializable{

//    this is omitted because all standings are always necessary to get
//    private List<String> filters;

    // think about composite key not the generated Integer id;
    @Id
    @GeneratedValue
    private Integer id;
    @OneToOne
    private Competition competition;
    @OneToOne
    private Season season;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Standing> standings;

}
