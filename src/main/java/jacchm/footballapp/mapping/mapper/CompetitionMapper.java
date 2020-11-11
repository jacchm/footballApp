package jacchm.footballapp.mapping.mapper;

import jacchm.footballapp.mapping.dto.CompetitionDTO;
import jacchm.footballapp.model.entity.Competition;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompetitionMapper {

    Competition mapToCompetition(CompetitionDTO competitionDTO);
    CompetitionDTO mapToCompetitionDTO(Competition competition);

}
