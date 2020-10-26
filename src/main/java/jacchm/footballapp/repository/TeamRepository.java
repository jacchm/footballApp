package jacchm.footballapp.repository;

import jacchm.footballapp.model.entity.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, Integer> {

    List<Team> findByCompetitionId(Integer competitionId);

}
