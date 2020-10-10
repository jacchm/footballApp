package jacchm.footballapp.repository;

import jacchm.footballapp.model.entity.StandingsInput;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface StandingInputRepository extends CrudRepository<StandingsInput, Integer> {

    Optional<List<StandingsInput>> findByCompetition_Id(Integer id);

}
