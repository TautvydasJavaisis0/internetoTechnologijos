package springframework.randomApp.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import springframework.randomApp.domain.Cat;

@Repository
public interface CatRepository extends CrudRepository<Cat, String> {
    void deleteById(String s);
}
