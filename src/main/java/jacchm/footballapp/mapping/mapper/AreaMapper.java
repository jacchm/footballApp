package jacchm.footballapp.mapping.mapper;

import jacchm.footballapp.mapping.dto.AreaDTO;
import jacchm.footballapp.model.entity.Area;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AreaMapper {

    AreaDTO areaToAreaDto(Area area);
    Area areaDtoToArea(AreaDTO areaDTO);


}
