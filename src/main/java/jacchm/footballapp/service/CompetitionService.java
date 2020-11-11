package jacchm.footballapp.service;

import jacchm.footballapp.mapping.dto.CompetitionDTO;

import java.util.List;

public interface CompetitionService {

    void deleteAll();
    void updateAll();
    List<CompetitionDTO> getAll();
    CompetitionDTO getById(Integer id);

}
