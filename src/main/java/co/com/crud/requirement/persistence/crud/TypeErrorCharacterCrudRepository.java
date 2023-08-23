package co.com.crud.requirement.persistence.crud;

import co.com.crud.requirement.persistence.entity.TypeErrorCharacterEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TypeErrorCharacterCrudRepository extends CrudRepository<TypeErrorCharacterEntity, Integer> {
    Optional<TypeErrorCharacterEntity> findById(Integer id);
}