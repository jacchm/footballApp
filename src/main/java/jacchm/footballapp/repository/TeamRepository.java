package jacchm.footballapp.repository;

import jacchm.footballapp.model.entity.Team;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Integer> {
}
