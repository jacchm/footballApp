package jacchm.footballapp.mapping.mapper;

import jacchm.footballapp.mapping.dto.SeasonDTO;
import jacchm.footballapp.model.entity.Season;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SeasonMapper {

    Season seasonDtoToSeason(SeasonDTO season);
    SeasonDTO seasonToSeasonDto(Season season);


}
