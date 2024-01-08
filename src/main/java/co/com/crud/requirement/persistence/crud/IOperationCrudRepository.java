package co.com.crud.requirement.persistence.crud;

import co.com.crud.requirement.persistence.entity.OperationEntity;
import org.springframework.data.repository.CrudRepository;

public interface IOperationCrudRepository extends CrudRepository<OperationEntity, Integer> {
}
