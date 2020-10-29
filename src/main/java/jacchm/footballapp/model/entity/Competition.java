package jacchm.footballapp.model.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
public class Competition implements Serializable {

    @Id
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Area area;
    private String name;
    private String code;
    private String ensignUrl;
    private String plan;
    @OneToOne(cascade = CascadeType.ALL)
    private Season currentSeason;
    private Integer numberOfAvailableSeasons;
    private LocalDateTime lastUpdated;


}
