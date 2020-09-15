package jacchm.footballapp.pojo.competition;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Winner {

    @Id
    private Integer id;
    private String name;
    private String shortName;
    private String tla;
    private String crestURL;

}
