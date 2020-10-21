package jacchm.footballapp.mapping.dto;

import lombok.Data;

@Data
public class LeagueTablePositionDTO {

    private Integer competitionId;
    private String type;
    private TeamDTO team;
    private Integer position;
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
