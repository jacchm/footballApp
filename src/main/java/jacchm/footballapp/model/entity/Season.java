package jacchm.footballapp.model.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
public class Season implements Serializable {

    private static final long serialVersionUID = -2885231369162303031L;
    @Id
    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    private int currentMatchday;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Team winner;

}
