package jacchm.footballapp.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
public class Standing implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String stage;
    // json input 'type' - disallowed keyword in MySql
    private String standingType;
    // json input 'group' - disallowed keyword in MySql
    private String standingGroup;
    // json input 'table' - disallowed keyword in MySql
    @OneToMany(cascade = CascadeType.ALL)
    private List<TableInput> leagueTable;

}

