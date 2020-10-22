package jacchm.footballapp.repository;

import jacchm.footballapp.model.entity.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends CrudRepository<Team, Integer> {

//    List<Team> findByAreaId(Integer areaId);
    List<Team> findByCompetitionId(Integer competitionId);

}
