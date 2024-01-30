package co.com.crud.requirement.persistence;

import co.com.crud.requirement.domain.model.TypeErrorCharacteristic;
import co.com.crud.requirement.domain.model.queryresult.IErrorDistributionAllRequirements;
import co.com.crud.requirement.domain.model.queryresult.IRequirementsByTypeAndCauseError;
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

    private final ITypeErrorCharacteristicCrudRepository typeErrorCharacteristicCrudRepository;

    private final TypeErrorCharacteristicMapper typeErrorCharacteristicMapper;

    @Autowired
    public TypeErrorCharacteristicRepository(ITypeErrorCharacteristicCrudRepository typeErrorCharacteristicCrudRepository, TypeErrorCharacteristicMapper typeErrorCharacteristicMapper) {
        this.typeErrorCharacteristicCrudRepository = typeErrorCharacteristicCrudRepository;
        this.typeErrorCharacteristicMapper = typeErrorCharacteristicMapper;
    }

    @Override
    public List<TypeErrorCharacteristic> getAllTypesErrors() {
        List<TypeErrorCharacteristicEntity> typesErrorsEntities = (List<TypeErrorCharacteristicEntity>) typeErrorCharacteristicCrudRepository.findAll();
        return typeErrorCharacteristicMapper.toTypesErrorsEntities(typesErrorsEntities);
    }

    @Override
    public Optional<TypeErrorCharacteristic> getTypeErrorById(Integer id) {
        return typeErrorCharacteristicCrudRepository.findById(id).map(typeErrorCharacteristicMapper::toTypeError);
    }

    @Override
    public int countRequirementsByTypeAndCauseError(String typeRequirement, String causeError) {
        return typeErrorCharacteristicCrudRepository.countRequirementsByTypeAndCauseError(typeRequirement, causeError);
    }

    @Override
    public int countRequirementByCauseErrorDDE() {
        return typeErrorCharacteristicCrudRepository.countRequirementsByCauseErrorDDE();
    }

    @Override
    public int countRequirementByCauseErrorDII() {
        return typeErrorCharacteristicCrudRepository.countRequirementsByCauseErrorDII();
    }

    @Override
    public int countRequirementByCauseErrorVAR() {
        return typeErrorCharacteristicCrudRepository.countRequirementsByCauseErrorVAR();
    }

    @Override
    public int countRequirementsByCauseErrorAndRequirementId(Integer requirementId, Integer typeErrorId, String causeError) {
        return typeErrorCharacteristicCrudRepository.countRequirementsByCauseErrorAndRequirementId(requirementId, typeErrorId, causeError);
    }

    @Override
    public int countTypeErrorEIEByRequirement(Integer requirementId) {
        return typeErrorCharacteristicCrudRepository.countTypeErrorEIEByRequirement(requirementId);
    }

    @Override
    public int countTypeErrorEIEAndCauseErrorDDEByRequirement(Integer requirementId) {
        return typeErrorCharacteristicCrudRepository.countTypeErrorEIEAndCauseErrorDDEByRequirement(requirementId);
    }

    @Override
    public int countTypeErrorEIEAndCauseErrorDIIByRequirement(Integer requirementId) {
        return typeErrorCharacteristicCrudRepository.countTypeErrorEIEAndCauseErrorDIIByRequirement(requirementId);
    }

    @Override
    public int countTypeErrorEIEAndCauseErrorVARByRequirement(Integer requirementId) {
        return typeErrorCharacteristicCrudRepository.countTypeErrorEIEAndCauseErrorVARByRequirement(requirementId);
    }

    @Override
    public int countTypeErrorMCCByRequirement(Integer requirementId) {
        return typeErrorCharacteristicCrudRepository.countTypeErrorMCCByRequirement(requirementId);
    }

    @Override
    public int countTypeErrorMCCAndCauseErrorDDEByRequirement(Integer requirementId) {
        return typeErrorCharacteristicCrudRepository.countTypeErrorMCCAndCauseErrorDDEByRequirement(requirementId);
    }

    @Override
    public int countTypeErrorMCCAndCauseErrorDIIByRequirement(Integer requirementId) {
        return typeErrorCharacteristicCrudRepository.countTypeErrorMCCAndCauseErrorDIIByRequirement(requirementId);
    }

    @Override
    public int countTypeErrorMCCAndCauseErrorVARByRequirement(Integer requirementId) {
        return typeErrorCharacteristicCrudRepository.countTypeErrorMCCAndCauseErrorVARByRequirement(requirementId);
    }

    public int countTypeErrorsByRequirements(Integer typeErrorId, Integer requirementId) {
        return typeErrorCharacteristicCrudRepository.countTypeErrorsByRequirements(typeErrorId, requirementId);
    }

    @Override
    public int countAllTypeErrorsByRequirement(Integer requirementId) {
        return typeErrorCharacteristicCrudRepository.countAllTypeErrorsByRequirement(requirementId);
    }

    @Override
    public int countAllCauseErrorsByRequirement(Integer requirementId) {
        return typeErrorCharacteristicCrudRepository.countAllCauseErrorsByRequirement(requirementId);
    }

    @Override
    public IRequirementsByTypeAndCauseError causeErrorByCharacteristicForRequirements(String typeRequirement,Integer projectId) {
        return typeErrorCharacteristicCrudRepository.causeErrorByCharacteristicForRequirements(typeRequirement,projectId);
    }

    @Override
    public IErrorDistributionAllRequirements errorDistributionAllRequirements(String typeRequirement, Integer projectId) {
        return typeErrorCharacteristicCrudRepository.errorDistributionAllRequirements(typeRequirement,projectId);
    }

}
