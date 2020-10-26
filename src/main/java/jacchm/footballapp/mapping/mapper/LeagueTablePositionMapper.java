package jacchm.footballapp.mapping.mapper;

import jacchm.footballapp.mapping.dto.LeagueTablePositionDTO;
import jacchm.footballapp.model.entity.LeagueTablePosition;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = TeamMapper.class, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface LeagueTablePositionMapper {

    @Mapping(target = "leagueTablePositionId.competitionId", source = "competitionId")
    @Mapping(target = "leagueTablePositionId.standingType", source = "type")
    @Mapping(target = "leagueTablePositionId.team", source = "team")
    LeagueTablePosition mapToLeagueTablePosition(LeagueTablePositionDTO leagueTablePositionDTO);

    @Mapping(target = "competitionId", source = "leagueTablePositionId.competitionId")
    @Mapping(target = "type", source = "leagueTablePositionId.standingType")
    @Mapping(target = "team", source = "leagueTablePositionId.team")
    LeagueTablePositionDTO mapToLeagueTablePositionDTO(LeagueTablePosition leagueTablePosition);

}
