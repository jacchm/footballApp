package jacchm.footballapp.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class Area implements Serializable {

    @Id
    private Integer id;
    private String name;
    private String countryCode;
    private String ensignUrl;


}
