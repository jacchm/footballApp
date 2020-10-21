package jacchm.footballapp.mapping.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SeasonDTO {

    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer currentMatchday;
    private TeamDTO winner;


}
