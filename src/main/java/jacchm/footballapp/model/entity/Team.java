package jacchm.footballapp.model.entity;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Embeddable
public class Team implements Serializable {

    @Id
    private Integer id;
    @ManyToOne
    private Area area;
    private String name;
    private String shortName;
    private String tla;
    private String crestUrl;
    private String address;
    private String phone;
    private String website;
    private String email;
    private String founded;
    private String clubColors;
    private String venue;
    private LocalDate lastUpdated;

}
