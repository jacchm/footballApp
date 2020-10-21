package jacchm.footballapp.mapping.mapper;

import jacchm.footballapp.mapping.dto.TeamDTO;
import jacchm.footballapp.model.entity.Team;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    Team teamDtoToTeam(TeamDTO teamDTO);
    TeamDTO teamToTeamDto(Team team);

}
