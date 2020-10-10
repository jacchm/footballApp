package jacchm.footballapp.mapping.mapper;

import jacchm.footballapp.mapping.dto.SeasonDTO;
import jacchm.footballapp.model.entity.Season;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SeasonMapper {

    SeasonMapper INSTANCE = Mappers.getMapper(SeasonMapper.class);

    Season seasonDtoToSeason(SeasonDTO season);
    SeasonDTO seasonToSeasonDto(Season season);

}
