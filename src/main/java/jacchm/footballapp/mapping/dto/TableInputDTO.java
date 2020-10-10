package jacchm.footballapp.mapping.dto;

import lombok.Data;

@Data
public class TableInputDTO {

    private Integer id;
    private Integer position;
    private TeamDTO team;
    private Integer playedGames;
    private String form;
    private Integer won;
    private Integer draw;
    private Integer lost;
    private Integer points;
    private Integer goalsFor;
    private Integer goalsAgainst;
    private Integer goalDifference;


}
