package co.com.crud.requirement.persistence;

import co.com.crud.requirement.domain.model.Character;
import co.com.crud.requirement.domain.repository.CharacterDomainRepository;
import co.com.crud.requirement.persistence.crud.CharacterCrudRepository;
import co.com.crud.requirement.persistence.entity.CharacterEntity;
import co.com.crud.requirement.persistence.mapper.CharacterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CharacterRepository implements CharacterDomainRepository {

    private final CharacterCrudRepository characterCrudRepository;

    private final CharacterMapper characterMapper;

    @Autowired
    public CharacterRepository(CharacterCrudRepository characterCrudRepository, CharacterMapper characterMapper) {
        this.characterCrudRepository = characterCrudRepository;
        this.characterMapper = characterMapper;
    }

    @Override
    public List<Character> getAllCharacters() {
       List<CharacterEntity> characterEntities = (List<CharacterEntity>) characterCrudRepository.findAll();
       return characterMapper.toCharactersEntity(characterEntities);
    }

    @Override
    public Optional<Character> getCharacterById(Integer id) {
        return characterCrudRepository.findById(id).map(characterMapper::toCharacter);
    }
}
