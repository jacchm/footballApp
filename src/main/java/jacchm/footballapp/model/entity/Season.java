package jacchm.footballapp.model.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Data
@Entity
public class Season {

    @Id
    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer currentMatchday;
    @OneToOne(cascade = CascadeType.ALL)
    private Winner winner;

}
