package jacchm.footballapp.service;

import jacchm.footballapp.mapping.dto.CompetitionDTO;

import java.util.List;

public interface CompetitionService {

    boolean deleteAll();
    boolean updateAll();
    List<CompetitionDTO> getAll();
    CompetitionDTO getById(Integer id);

}
