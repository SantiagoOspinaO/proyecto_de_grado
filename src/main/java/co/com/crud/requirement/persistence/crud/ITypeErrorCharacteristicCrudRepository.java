package co.com.crud.requirement.persistence.crud;

import co.com.crud.requirement.persistence.entity.TypeErrorCharacteristicEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ITypeErrorCharacteristicCrudRepository extends CrudRepository<TypeErrorCharacteristicEntity, Integer> {
    Optional<TypeErrorCharacteristicEntity> findById(Integer id);
}