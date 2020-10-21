package jacchm.footballapp.repository;

import jacchm.footballapp.model.entity.Area;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepository extends CrudRepository<Area, Integer> {

}
