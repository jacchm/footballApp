package jacchm.footballapp.pojo.competition;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class CurrentSeason {

    @Id
    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer currentMatchday;
    @OneToOne(cascade = CascadeType.ALL)
    private Winner winner;

}
