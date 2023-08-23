package co.com.crud.requirement.domain.repository;

import co.com.crud.requirement.domain.model.TypeErrorCharacter;

import java.util.List;
import java.util.Optional;

public interface TypeErrorCharacterDomainRepository {

    List<TypeErrorCharacter> getAllTypesErrors();

    Optional<TypeErrorCharacter> getTypeErrorById(Integer id);
}