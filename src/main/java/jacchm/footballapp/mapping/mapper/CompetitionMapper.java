package jacchm.footballapp.mapping.mapper;

import jacchm.footballapp.mapping.dto.CompetitionDTO;
import jacchm.footballapp.model.entity.Competition;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompetitionMapper {

    CompetitionMapper INSTANCE = Mappers.getMapper(CompetitionMapper.class);

    Competition competitionDtoToCompetition(CompetitionDTO competitionDTO);
    CompetitionDTO competitionToCompetitionDTO(Competition competition);
}
