package jacchm.footballapp.model.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
public class Competition implements Serializable {

    @Id
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Area area;
    private String name;
    private String code;
    private String ensignUrl;
    private String plan;
    @OneToOne(cascade = CascadeType.ALL)
    private Season currentSeason;
    @NotNull
    private Integer numberOfAvailableSeasons;
    private LocalDateTime lastUpdated;

}
