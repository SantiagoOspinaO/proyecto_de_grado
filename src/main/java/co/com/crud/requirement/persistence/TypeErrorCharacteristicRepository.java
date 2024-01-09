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
    public int countRequirementsByTypeAndCauseError(String typeRequirement, String causeError) {
        return ITypeErrorCharacteristicCrudRepository.countRequirementsByTypeAndCauseError(typeRequirement, causeError);
    }

    @Override
    public int countRequirementByCauseErrorDDE() {
        return ITypeErrorCharacteristicCrudRepository.countRequirementsByCauseErrorDDE();
    }

    @Override
    public int countRequirementByCauseErrorDII() {
        return ITypeErrorCharacteristicCrudRepository.countRequirementsByCauseErrorDII();
    }

    @Override
    public int countRequirementByCauseErrorVAR() {
        return ITypeErrorCharacteristicCrudRepository.countRequirementsByCauseErrorVAR();
    }

    @Override
    public int countRequirementsByCauseErrorAndRequirementId(Integer requirementId, Integer typeErrorId, String causeError) {
        return ITypeErrorCharacteristicCrudRepository.countRequirementsByCauseErrorAndRequirementId(requirementId, typeErrorId, causeError);
    }

    @Override
    public int countTypeErrorEIEByRequirement(Integer requirementId) {
        return ITypeErrorCharacteristicCrudRepository.countTypeErrorEIEByRequirement(requirementId);
    }

    @Override
    public int countTypeErrorEIEAndCauseErrorDDEByRequirement(Integer requirementId) {
        return ITypeErrorCharacteristicCrudRepository.countTypeErrorEIEAndCauseErrorDDEByRequirement(requirementId);
    }

    @Override
    public int countTypeErrorEIEAndCauseErrorDIIByRequirement(Integer requirementId) {
        return ITypeErrorCharacteristicCrudRepository.countTypeErrorEIEAndCauseErrorDIIByRequirement(requirementId);
    }

    @Override
    public int countTypeErrorEIEAndCauseErrorVARByRequirement(Integer requirementId) {
        return ITypeErrorCharacteristicCrudRepository.countTypeErrorEIEAndCauseErrorVARByRequirement(requirementId);
    }

    @Override
    public int countTypeErrorMCCByRequirement(Integer requirementId) {
        return ITypeErrorCharacteristicCrudRepository.countTypeErrorMCCByRequirement(requirementId);
    }

    @Override
    public int countTypeErrorMCCAndCauseErrorDDEByRequirement(Integer requirementId) {
        return ITypeErrorCharacteristicCrudRepository.countTypeErrorMCCAndCauseErrorDDEByRequirement(requirementId);
    }

    @Override
    public int countTypeErrorMCCAndCauseErrorDIIByRequirement(Integer requirementId) {
        return ITypeErrorCharacteristicCrudRepository.countTypeErrorMCCAndCauseErrorDIIByRequirement(requirementId);
    }

    @Override
    public int countTypeErrorMCCAndCauseErrorVARByRequirement(Integer requirementId) {
        return ITypeErrorCharacteristicCrudRepository.countTypeErrorMCCAndCauseErrorVARByRequirement(requirementId);
    }

    public int countTypeErrorsByRequirements(Integer typeErrorId, Integer requirementId) {
        return ITypeErrorCharacteristicCrudRepository.countTypeErrorsByRequirements(typeErrorId, requirementId);
    }

    @Override
    public int countAllTypeErrorsByRequirement(Integer requirementId) {
        return ITypeErrorCharacteristicCrudRepository.countAllTypeErrorsByRequirement(requirementId);
    }

    @Override
    public int countAllCauseErrorsByRequirement(Integer requirementId) {
        return ITypeErrorCharacteristicCrudRepository.countAllCauseErrorsByRequirement(requirementId);
    }

    @Override
    public IRequirementsByTypeAndCauseError causeErrorByCharacteristicForRequirements(Integer projectId) {
        return ITypeErrorCharacteristicCrudRepository.causeErrorByCharacteristicForRequirements(projectId);
    }

    @Override
    public IErrorDistributionAllRequirements errorDistributionAllRequirements(Integer projectId) {
        return ITypeErrorCharacteristicCrudRepository.errorDistributionAllRequirements(projectId);
    }

}
