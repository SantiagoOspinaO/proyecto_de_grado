package co.com.crud.requirement.domain.repository;

import co.com.crud.requirement.domain.model.Characteristic;
import co.com.crud.requirement.domain.model.queryresult.ICharacteristicsByRequirementId;
import co.com.crud.requirement.domain.model.queryresult.IGradeCharacteristicByRequirementId;

import java.util.List;
import java.util.Optional;

public interface CharacteristicDomainRepository {

    List<Characteristic> getAllCharacteristics();

    Optional<Characteristic> getCharacteristicById(Integer id);

    List<IGradeCharacteristicByRequirementId> getGradesCharacteristicByRequirementId(Integer requirementId);

    List<ICharacteristicsByRequirementId> getCharacteristicsByRequirementId(Integer requirementId);

    void updateCharacteristicByRequirementId(Integer requirementId, Integer characteristicId, String name, String description, String oppositeName, String oppositeDescription, Double gradeCharacteristic, boolean dde, boolean dii, boolean var);

    void updateGradeCharacteristicByRequirement(Double gradeInput, Integer requirementId, Integer characteristicId);

    void updateTypeErrorOfCharacteristic(boolean dde, boolean dii, boolean var, Integer requirementId, Integer characteristicId);

    int countRequirementsByTypeAndNameCharacteristic(String typeRequirement, String nameCharacteristic);
}
