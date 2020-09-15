package jacchm.footballapp.repository;

import jacchm.footballapp.pojo.competition.Winner;
import org.springframework.data.repository.CrudRepository;

public interface WinnerRepository extends CrudRepository<Winner, Integer> {
}
