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

    @Override
    public int countRequirementsByTypeAndTypeError(String typeRequirement, String typeError) {
        return ITypeErrorCharacteristicCrudRepository.countRequirementsByTypeAndTypeError(typeRequirement, typeError);
    }

    @Override
    public int countRequirementByErrorDDE() {
        return ITypeErrorCharacteristicCrudRepository.countRequirementsByErrorDDE();
    }

    @Override
    public int countRequirementByErrorDII() {
        return ITypeErrorCharacteristicCrudRepository.countRequirementsByErrorDII();
    }

    @Override
    public int countRequirementByErrorVAR() {
        return ITypeErrorCharacteristicCrudRepository.countRequirementsByErrorVAR();
    }

    @Override
    public int countTypeErrorEIEByRequirement(Integer requirementId) {
        return ITypeErrorCharacteristicCrudRepository.countTypeErrorEIEByRequirement(requirementId);
    }

    @Override
    public int countTypeErrorMCCByRequirement(Integer requirementId) {
        return ITypeErrorCharacteristicCrudRepository.countTypeErrorMCCByRequirement(requirementId);
    }

    @Override
    public int countTypeErrorsByRequirement(Integer requirementId) {
        return ITypeErrorCharacteristicCrudRepository.countTypeErrorsByRequirement(requirementId);
    }

}
