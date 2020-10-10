package jacchm.footballapp.mapping.mapper;

import jacchm.footballapp.mapping.dto.AreaDTO;
import jacchm.footballapp.model.entity.Area;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AreaMapper {

    AreaMapper INSTANCE = Mappers.getMapper(AreaMapper.class);

    AreaDTO areaToAreaDto(Area area);
    Area areaDtoToArea(AreaDTO areaDTO);

}
