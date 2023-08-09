package co.com.crud.requirement.domain.repository;

import co.com.crud.requirement.domain.model.Character;

import java.util.List;
import java.util.Optional;

public interface CharacterDomainRepository {

    List<Character> getAllCharacters();

    Optional<Character> getCharacterById(Integer id);

}
