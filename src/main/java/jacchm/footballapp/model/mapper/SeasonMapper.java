package jacchm.footballapp.model.mapper;

import jacchm.footballapp.model.dto.SeasonDTO;
import jacchm.footballapp.model.entity.Season;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SeasonMapper {

    SeasonMapper INSTANCE = Mappers.getMapper(SeasonMapper.class);

    Season seasonDtoToSeason(SeasonDTO season);
    SeasonDTO seasonToSeasonDto(Season season);

}
