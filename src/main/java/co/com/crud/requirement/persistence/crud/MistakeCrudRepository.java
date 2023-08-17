package co.com.crud.requirement.persistence.crud;

import co.com.crud.requirement.persistence.entity.MistakeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
public interface MistakeCrudRepository extends CrudRepository<MistakeEntity, Integer> {
        Optional<MistakeEntity> findById(Integer id);
}
