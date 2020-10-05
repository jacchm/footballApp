package jacchm.footballapp.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CompetitionDTO {

    private int id;
    private AreaDTO area;
    private String name;
    private String code;
    private String ensignUrl;
    private String plan;
    private SeasonDTO currentSeason;
    private int numberOfAvailableSeasons;
    private LocalDateTime lastUpdated;

}
