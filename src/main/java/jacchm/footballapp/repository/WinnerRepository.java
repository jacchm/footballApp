package jacchm.footballapp.repository;

import jacchm.footballapp.model.entity.Winner;
import org.springframework.data.repository.CrudRepository;

public interface WinnerRepository extends CrudRepository<Winner, Integer> {
}
