package co.com.crud.requirement.domain.service;

import co.com.crud.requirement.domain.exception.TypeErrorNotFoundException;
import co.com.crud.requirement.domain.model.TypeErrorCharacter;
import co.com.crud.requirement.domain.repository.TypeErrorCharacterDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeErrorCharacterService {

    private final TypeErrorCharacterDomainRepository typeErrorCharacterDomainRepository;

    @Autowired
    public TypeErrorCharacterService(TypeErrorCharacterDomainRepository typeErrorCharacterDomainRepository) {
        this.typeErrorCharacterDomainRepository = typeErrorCharacterDomainRepository;
    }

    public List<TypeErrorCharacter> getAllTypesErrors() {
        return typeErrorCharacterDomainRepository.getAllTypesErrors();
    }

    public Optional<TypeErrorCharacter> getTypeErrorById(Integer id) {
        Optional<TypeErrorCharacter> typeError = typeErrorCharacterDomainRepository.getTypeErrorById(id);
        if (typeError.isEmpty()) {
            throw new TypeErrorNotFoundException(id);
        }
        return typeError;
    }
}
