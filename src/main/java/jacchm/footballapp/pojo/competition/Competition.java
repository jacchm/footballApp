package jacchm.footballapp.pojo.competition;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Competition {

    @Id
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Area area;
    private String name;
    private String code;
    private String ensignUrl;
    private String plan;
    @OneToOne(cascade = CascadeType.ALL)
    private CurrentSeason currentSeason;
    private Integer numberOfAvailableSeasons;
    private LocalDateTime lastUpdated;

}
