package jacchm.footballapp.repository;

import jacchm.footballapp.model.entity.Season;
import org.springframework.data.repository.CrudRepository;

public interface CurrentSeasonRepository extends CrudRepository<Season, Integer> {

}
