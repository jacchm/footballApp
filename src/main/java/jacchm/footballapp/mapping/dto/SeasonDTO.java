package jacchm.footballapp.mapping.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SeasonDTO {

    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private int currentMatchday;
    private TeamDTO winner;

}
