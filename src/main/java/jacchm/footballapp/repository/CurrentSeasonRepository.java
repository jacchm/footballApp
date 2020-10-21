package jacchm.footballapp.repository;

import jacchm.footballapp.model.entity.Season;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentSeasonRepository extends CrudRepository<Season, Integer> {

}
