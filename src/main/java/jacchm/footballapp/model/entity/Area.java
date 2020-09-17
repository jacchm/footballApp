package jacchm.footballapp.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Area {

    @Id
    private Integer id;
    private String name;
    private String countryCode;
    private String ensignUrl;

}
