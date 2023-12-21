package co.com.crud.requirement.domain.repository;

import co.com.crud.requirement.domain.model.TypeErrorCharacteristic;
import java.util.List;
import java.util.Optional;

public interface TypeErrorCharacteristicDomainRepository {

    List<TypeErrorCharacteristic> getAllTypesErrors();

    Optional<TypeErrorCharacteristic> getTypeErrorById(Integer id);

    int countRequirementsByTypeAndCauseError(String typeRequirement, String causeError);

    int countRequirementByCauseErrorDDE();

    int countRequirementByCauseErrorDII();

    int countRequirementByCauseErrorVAR();

    int countRequirementsByCauseErrorAndRequirementId(Integer requirementId, String causeError);

    int countTypeErrorEIEByRequirement(Integer requirementId);

    int countTypeErrorMCCByRequirement(Integer requirementId);

    int countTypeErrorsByRequirements(Integer typeErrorId, Integer requirementId);

    int countAllTypeErrorsByRequirement(Integer requirementId);

    int countAllCauseErrorsByRequirement(Integer requirementId);

}