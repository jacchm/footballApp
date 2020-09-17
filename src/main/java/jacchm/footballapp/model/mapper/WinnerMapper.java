package jacchm.footballapp.model.mapper;

import jacchm.footballapp.model.dto.WinnerDTO;
import jacchm.footballapp.model.entity.Winner;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WinnerMapper {

    WinnerMapper INSTANCE = Mappers.getMapper(WinnerMapper.class);

    Winner winnerDtoToWinner(WinnerDTO winnerDTO);
    WinnerDTO winnerToWinnerDto(Winner winner);

}
