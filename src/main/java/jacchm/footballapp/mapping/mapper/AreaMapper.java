package jacchm.footballapp.mapping.mapper;

import jacchm.footballapp.mapping.dto.AreaDTO;
import jacchm.footballapp.model.entity.Area;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AreaMapper {

    AreaDTO mapToAreaDto(Area area);
    Area mapToArea(AreaDTO areaDTO);

}
