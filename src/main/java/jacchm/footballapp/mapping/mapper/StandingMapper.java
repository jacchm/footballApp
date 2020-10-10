package jacchm.footballapp.mapping.mapper;

import jacchm.footballapp.mapping.dto.StandingDTO;
import jacchm.footballapp.model.entity.Standing;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StandingMapper {

    StandingMapper INSTANCE = Mappers.getMapper(StandingMapper.class);

    @Mappings({
            @Mapping(target = "standingType", source = "standingDTO.type"),
            @Mapping(target = "standingGroup", source = "standingDTO.group" ),
            @Mapping(target = "leagueTable", source = "standingDTO.table")
    })
    Standing standingDtoToStanding(StandingDTO standingDTO);

    @Mappings({
            @Mapping(target = "type", source = "standing.standingType"),
            @Mapping(target = "group", source = "standing.standingGroup"),
            @Mapping(target = "table", source = "standing.leagueTable")
    })
    StandingDTO standingToStandingDto(Standing standing);

}
