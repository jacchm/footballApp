package jacchm.footballapp.model.mapper;

import jacchm.footballapp.model.dto.CompetitionDTO;
import jacchm.footballapp.model.entity.Competition;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompetitionMapper {

    CompetitionMapper INSTANCE = Mappers.getMapper(CompetitionMapper.class);

    Competition competitionDtoToCompetition(CompetitionDTO competitionDTO);
    CompetitionDTO competitionToCompetitionDTO(Competition competition);
}
