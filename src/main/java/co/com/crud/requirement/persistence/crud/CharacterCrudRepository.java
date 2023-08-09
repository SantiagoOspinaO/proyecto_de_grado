package co.com.crud.requirement.persistence.crud;

import co.com.crud.requirement.persistence.entity.CharacterEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CharacterCrudRepository extends CrudRepository<CharacterEntity, Integer> {
    Optional<CharacterEntity> findById(Integer id);
}
