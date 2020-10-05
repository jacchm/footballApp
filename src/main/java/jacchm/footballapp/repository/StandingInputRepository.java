package jacchm.footballapp.repository;

import jacchm.footballapp.model.entity.StandingsInput;
import org.springframework.data.repository.CrudRepository;

public interface StandingInputRepository extends CrudRepository<StandingsInput, Integer> {
}
