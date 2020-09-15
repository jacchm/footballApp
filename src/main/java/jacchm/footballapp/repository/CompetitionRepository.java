package jacchm.footballapp.repository;

import jacchm.footballapp.pojo.competition.Competition;
import org.springframework.data.repository.CrudRepository;

public interface CompetitionRepository extends CrudRepository<Competition, Integer> {
}
