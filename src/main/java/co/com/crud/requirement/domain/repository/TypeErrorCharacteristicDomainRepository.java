package co.com.crud.requirement.domain.repository;

import co.com.crud.requirement.domain.model.TypeErrorCharacteristic;

import java.util.List;
import java.util.Optional;

public interface TypeErrorCharacteristicDomainRepository {

    List<TypeErrorCharacteristic> getAllTypesErrors();

    Optional<TypeErrorCharacteristic> getTypeErrorById(Integer id);

    int countRequirementsByTypeAndCauseError(String typeRequirement, String causeError);

    int countRequirementByErrorDDE();

    int countRequirementByErrorDII();

    int countRequirementByErrorVAR();

    int countTypeErrorEIEByRequirement(Integer requirementId);

    int countTypeErrorMCCByRequirement(Integer requirementId);

    int countTypeErrorsByRequirement(Integer requirementId);
}