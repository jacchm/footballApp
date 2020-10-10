package jacchm.footballapp.mapping.mapper;

import jacchm.footballapp.mapping.dto.TableInputDTO;
import jacchm.footballapp.model.entity.TableInput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TableInputMapper {

    TableInputMapper INSTANCE = Mappers.getMapper(TableInputMapper.class);

    TableInput TableInputDtoToTableInput(TableInputDTO tableInputDTO);
    TableInputDTO TableInputToTableInputDto(TableInput tableInput);


}
