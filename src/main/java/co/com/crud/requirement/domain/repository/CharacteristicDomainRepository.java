package co.com.crud.requirement.domain.repository;

import co.com.crud.requirement.domain.model.Characteristic;
import co.com.crud.requirement.domain.model.queryresult.*;

import java.util.List;
import java.util.Optional;

public interface CharacteristicDomainRepository {

    List<Characteristic> getAllCharacteristics();

    Optional<Characteristic> getCharacteristicById(Integer id);

    List<IGradeCharacteristicByRequirementId> getGradesCharacteristicByRequirementId(Integer requirementId);

    List<ICharacteristicsByRequirementId> getCharacteristicsByRequirementId(Integer requirementId);

    void updateGradeCharacteristicByRequirement(Double gradeInput, Integer requirementId, Integer characteristicId);

    void updateCauseErrorOfCharacteristic(boolean dde, boolean dii, boolean ceVar, Integer requirementId, Integer characteristicId);

    IRequirementsByTypeAndNameCharacteristic countRequirementsByTypeAndNameCharacteristic(String typeRequirement, Integer projectId);

    IRequirementsByRequirementIdAndCauseError countRequirementsByRequirementIdAndCauseError(Integer requirementId, Integer projectId);

    IRequirementsByFilterCauseError countCauseErrorByRequirementType(String typeRequirement, Integer projectId);

    IRequirementsByTypeAndCauseError countRequirementsByTypeAndCauseError(String typeRequirement, Integer projectId);

    ICharacteristicsByCauseError countCharacteristicsByCauseErrorDDE(String typeRequirement, Integer projectId);

    ICharacteristicsByCauseError countCharacteristicsByCauseErrorDII(String typeRequirement, Integer projectId);

    ICharacteristicsByCauseError countCharacteristicsByCauseErrorVAR(String typeRequirement, Integer projectId);

}
