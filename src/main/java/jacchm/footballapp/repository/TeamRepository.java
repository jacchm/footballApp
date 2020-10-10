package jacchm.footballapp.repository;

import jacchm.footballapp.model.entity.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends CrudRepository<Team, Integer> {

    Optional<List<Team>> findByAreaId (Integer areaId);

}
