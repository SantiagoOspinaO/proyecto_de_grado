package co.com.crud.requirement.persistence.crud;

import co.com.crud.requirement.persistence.entity.RequirementEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RequirementCrudRepository extends CrudRepository<RequirementEntity, Long> {
    Optional<RequirementEntity> findById(Integer id);
}