package jacchm.footballapp.mapping.mapper;

import jacchm.footballapp.mapping.dto.TeamDTO;
import jacchm.footballapp.model.entity.Team;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

    Team teamDtoToTeam(TeamDTO teamDTO);
    TeamDTO teamToTeamDto(Team team);


}
