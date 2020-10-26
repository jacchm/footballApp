package jacchm.footballapp.repository;

import jacchm.footballapp.model.entity.LeagueTablePosition;
import jacchm.footballapp.model.entity.LeagueTablePositionId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LeagueTablePositionRepository extends CrudRepository<LeagueTablePosition, LeagueTablePositionId> {

    List<LeagueTablePosition> findByLeagueTablePositionId_CompetitionId(Integer competitionId);
    List<LeagueTablePosition> findByLeagueTablePositionId_CompetitionIdAndLeagueTablePositionId_StandingTypeOrderByPosition(
            Integer competitionId, String type);

}
