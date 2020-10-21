package jacchm.footballapp.mapping.mapper;

import jacchm.footballapp.mapping.dto.CompetitionDTO;
import jacchm.footballapp.model.entity.Competition;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompetitionMapper {

    Competition competitionDtoToCompetition(CompetitionDTO competitionDTO);
    CompetitionDTO competitionToCompetitionDTO(Competition competition);

}
