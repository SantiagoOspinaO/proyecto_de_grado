package co.com.crud.requirement.persistence.crud;

import co.com.crud.requirement.persistence.entity.CharacteristicEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CharacteristicCrudRepository extends CrudRepository<CharacteristicEntity, Integer> {
    Optional<CharacteristicEntity> findById(Integer id);
}
