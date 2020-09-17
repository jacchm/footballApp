package jacchm.footballapp.model.mapper;

import jacchm.footballapp.model.dto.TableInputDTO;
import jacchm.footballapp.model.entity.TableInput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TableInputMapper {

    TableInputMapper INSTANCE = Mappers.getMapper(TableInputMapper.class);

    TableInput TableInputDtoToTableInput(TableInputDTO tableInputDTO);
    TableInputDTO TableInputToTableInputDto(TableInput tableInput);

}
