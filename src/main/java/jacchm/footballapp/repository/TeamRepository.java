package jacchm.footballapp.repository;

import jacchm.footballapp.model.entity.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, Integer> {

    @Query("from Team t join fetch t.area " +
            "where t.competitionId = :competitionId " +
            "order by t.name" )
    List<Team> findByCompetitionId(Integer competitionId);

}
