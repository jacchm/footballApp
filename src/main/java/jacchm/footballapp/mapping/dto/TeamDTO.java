package jacchm.footballapp.mapping.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TeamDTO {

    private int id;
    private AreaDTO area;
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
