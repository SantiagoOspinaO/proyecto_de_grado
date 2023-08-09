package co.com.crud.requirement.domain.service;

import co.com.crud.requirement.domain.exception.CharacterNotFoundException;
import co.com.crud.requirement.domain.model.Character;
import co.com.crud.requirement.domain.repository.CharacterDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterService {

    private final CharacterDomainRepository characterDomainRepository;

    @Autowired
    public CharacterService(CharacterDomainRepository characterDomainRepository) {
        this.characterDomainRepository = characterDomainRepository;
    }

    public List<Character> getAllCharacters(){
        return characterDomainRepository.getAllCharacters();
    }

    public Optional<Character> getCharacterById(Integer id) {
        Optional<Character> characters = characterDomainRepository.getCharacterById(id);
        if(characters.isEmpty()) {
            throw new CharacterNotFoundException(id);
        }
        return characters;
    }
}
