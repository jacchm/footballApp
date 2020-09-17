package jacchm.footballapp.repository;

import jacchm.footballapp.model.entity.Standing;
import org.springframework.data.repository.CrudRepository;

public interface StandingRepository extends CrudRepository<Standing, Integer> {
}
