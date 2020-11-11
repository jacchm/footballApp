package jacchm.footballapp.mapping.mapper;

import jacchm.footballapp.mapping.dto.SeasonDTO;
import jacchm.footballapp.model.entity.Season;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SeasonMapper {

    Season mapToSeason(SeasonDTO season);
    SeasonDTO mapToSeasonDto(Season season);

}
