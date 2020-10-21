package jacchm.footballapp.repository;

import jacchm.footballapp.model.entity.LeagueTablePosition;
import jacchm.footballapp.model.entity.LeagueTablePositionId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeagueTablePositionRepository extends CrudRepository<LeagueTablePosition, LeagueTablePositionId> {

    List<LeagueTablePosition> findByLeagueTablePositionId_CompetitionId(Integer competitionId);

}
