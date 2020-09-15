package jacchm.footballapp.repository;

import jacchm.footballapp.pojo.competition.CurrentSeason;
import org.springframework.data.repository.CrudRepository;

public interface CurrentSeasonRepository extends CrudRepository<CurrentSeason, Integer> {
}
