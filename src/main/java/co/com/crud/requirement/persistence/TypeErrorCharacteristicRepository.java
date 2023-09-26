package co.com.crud.requirement.persistence;

import co.com.crud.requirement.domain.model.TypeErrorCharacteristic;
import co.com.crud.requirement.domain.repository.TypeErrorCharacteristicDomainRepository;
import co.com.crud.requirement.persistence.crud.ITypeErrorCharacteristicCrudRepository;
import co.com.crud.requirement.persistence.entity.TypeErrorCharacteristicEntity;
import co.com.crud.requirement.persistence.mapper.TypeErrorCharacteristicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TypeErrorCharacteristicRepository implements TypeErrorCharacteristicDomainRepository {

    private final ITypeErrorCharacteristicCrudRepository ITypeErrorCharacteristicCrudRepository;

    private final TypeErrorCharacteristicMapper typeErrorCharacteristicMapper;

    @Autowired
    public TypeErrorCharacteristicRepository(ITypeErrorCharacteristicCrudRepository ITypeErrorCharacteristicCrudRepository, TypeErrorCharacteristicMapper typeErrorCharacteristicMapper) {
        this.ITypeErrorCharacteristicCrudRepository = ITypeErrorCharacteristicCrudRepository;
        this.typeErrorCharacteristicMapper = typeErrorCharacteristicMapper;
    }

    @Override
    public List<TypeErrorCharacteristic> getAllTypesErrors() {
        List<TypeErrorCharacteristicEntity> typesErrorsEntities = (List<TypeErrorCharacteristicEntity>) ITypeErrorCharacteristicCrudRepository.findAll();
        return typeErrorCharacteristicMapper.toTypesErrorsEntities(typesErrorsEntities);
    }

    @Override
    public Optional<TypeErrorCharacteristic> getTypeErrorById(Integer id) {
        return ITypeErrorCharacteristicCrudRepository.findById(id).map(typeErrorCharacteristicMapper::toTypeError);
    }
}
