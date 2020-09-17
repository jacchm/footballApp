package jacchm.footballapp.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Standing {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String stage;
    // json input 'type' - disallowed in MySql
    private String standingType;
    // json input 'group' - disallowed in MySql
    private String standingGroup;
    // json input 'table' - disallowed in MySql
    @OneToMany(cascade = CascadeType.ALL)
    private List<TableInput> leagueTable;

    public String getStandingType() {
        return standingType;
    }

    public void setStandingType(String standingType) {
        this.standingType = standingType;
    }

    public String getStandingGroup() {
        return standingGroup;
    }

    public void setStandingGroup(String standingGroup) {
        this.standingGroup = standingGroup;
    }

    public List<TableInput> getLeagueTable() {
        return leagueTable;
    }

    public void setLeagueTable(List<TableInput> leagueTable) {
        this.leagueTable = leagueTable;
    }
}

