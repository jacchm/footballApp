package jacchm.footballapp.repository;

import jacchm.footballapp.model.entity.LeagueTablePosition;
import jacchm.footballapp.model.entity.LeagueTablePositionId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeagueTablePositionRepository extends CrudRepository<LeagueTablePosition, LeagueTablePositionId> {

    @Query("from LeagueTablePosition ltp join fetch ltp.leagueTablePositionId.team " +
            "join fetch ltp.leagueTablePositionId.team.area " +
            "where ltp.leagueTablePositionId.competitionId = :competitionId " +
            "order by ltp.position")
    List<LeagueTablePosition> findByCompetitionId(Integer competitionId);

    @Query("from LeagueTablePosition ltp join fetch ltp.leagueTablePositionId.team " +
            "join fetch ltp.leagueTablePositionId.team.area " +
            "where ltp.leagueTablePositionId.competitionId = :competitionId " +
            "and ltp.leagueTablePositionId.standingType = :type " +
            "order by ltp.position")
    List<LeagueTablePosition> findByCompetitionIdAndType(
            @Param("competitionId") Integer competitionId, @Param("type") String type);

}
