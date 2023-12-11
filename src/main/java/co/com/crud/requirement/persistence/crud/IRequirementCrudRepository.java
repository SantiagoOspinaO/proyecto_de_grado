package co.com.crud.requirement.persistence.crud;

import co.com.crud.requirement.persistence.entity.RequirementEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface IRequirementCrudRepository extends CrudRepository<RequirementEntity, Integer> {

    Optional<RequirementEntity> findById(Integer id);
}