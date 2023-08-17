package co.com.crud.requirement.persistence.crud;


import co.com.crud.requirement.persistence.entity.CauseErrorEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CauseErrorCrudRepository extends CrudRepository<CauseErrorEntity, Integer> {
    Optional<CauseErrorEntity> findById(Integer id);
}
