package jacchm.footballapp.mapping.mapper;

import jacchm.footballapp.mapping.dto.LeagueTablePositionDTO;
import jacchm.footballapp.model.entity.LeagueTablePosition;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = TeamMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface LeagueTablePositionMapper {

    @Mappings({
            @Mapping(target = "leagueTablePositionId.competitionId", source = "leagueTablePositionDTO.competitionId"),
            @Mapping(target = "leagueTablePositionId.standingType", source = "leagueTablePositionDTO.type"),
            @Mapping(target = "leagueTablePositionId.team", source = "leagueTablePositionDTO.team"),
    })
    LeagueTablePosition leagueTablePositionDTOToLeagueTablePosition(LeagueTablePositionDTO leagueTablePositionDTO);

    @Mappings({
            @Mapping(target = "competitionId", source = "leagueTablePosition.leagueTablePositionId.competitionId"),
            @Mapping(target = "type", source = "leagueTablePosition.leagueTablePositionId.standingType"),
            @Mapping(target = "team", source = "leagueTablePosition.leagueTablePositionId.team"),
    })
    LeagueTablePositionDTO leagueTablePositionToLeagueTablePositionDTO(LeagueTablePosition leagueTablePosition);

}
