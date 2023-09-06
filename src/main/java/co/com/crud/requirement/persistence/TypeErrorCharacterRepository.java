package co.com.crud.requirement.persistence;

import co.com.crud.requirement.domain.model.TypeErrorCharacteristic;
import co.com.crud.requirement.domain.repository.TypeErrorCharacterDomainRepository;
import co.com.crud.requirement.persistence.crud.TypeErrorCharacterCrudRepository;
import co.com.crud.requirement.persistence.entity.TypeErrorCharacterEntity;
import co.com.crud.requirement.persistence.mapper.TypeErrorCharacterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TypeErrorCharacterRepository implements TypeErrorCharacterDomainRepository {

    private final TypeErrorCharacterCrudRepository typeErrorCharacterCrudRepository;

    private final TypeErrorCharacterMapper typeErrorCharacterMapper;

    @Autowired
    public TypeErrorCharacterRepository(TypeErrorCharacterCrudRepository typeErrorCharacterCrudRepository, TypeErrorCharacterMapper typeErrorCharacterMapper) {
        this.typeErrorCharacterCrudRepository = typeErrorCharacterCrudRepository;
        this.typeErrorCharacterMapper = typeErrorCharacterMapper;
    }

    @Override
    public List<TypeErrorCharacteristic> getAllTypesErrors() {
        List<TypeErrorCharacterEntity> typesErrorsEntities = (List<TypeErrorCharacterEntity>) typeErrorCharacterCrudRepository.findAll();
        return typeErrorCharacterMapper.toTypesErrorsEntities(typesErrorsEntities);
    }

    @Override
    public Optional<TypeErrorCharacteristic> getTypeErrorById(Integer id) {
        return typeErrorCharacterCrudRepository.findById(id).map(typeErrorCharacterMapper::toTypeError);
    }
}
