package co.com.crud.requirement.persistence;

import co.com.crud.requirement.domain.model.Characteristic;
import co.com.crud.requirement.domain.model.queryresult.ICharacteristicsByRequirementId;
import co.com.crud.requirement.domain.model.queryresult.IGradeCharacteristicByRequirementId;
import co.com.crud.requirement.domain.repository.CharacteristicDomainRepository;
import co.com.crud.requirement.persistence.crud.ICharacteristicCrudRepository;
import co.com.crud.requirement.persistence.entity.CharacteristicEntity;
import co.com.crud.requirement.persistence.mapper.CharacteristicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CharacteristicRepository implements CharacteristicDomainRepository {

    private final ICharacteristicCrudRepository characteristicCrudRepository;

    private final CharacteristicMapper characteristicMapper;

    @Autowired
    public CharacteristicRepository(ICharacteristicCrudRepository characteristicCrudRepository, CharacteristicMapper characteristicMapper) {
        this.characteristicCrudRepository = characteristicCrudRepository;
        this.characteristicMapper = characteristicMapper;
    }

    @Override
    public List<Characteristic> getAllCharacteristics() {
        List<CharacteristicEntity> characterEntities = (List<CharacteristicEntity>) characteristicCrudRepository.findAll();
        return characteristicMapper.toCharacteristicsEntity(characterEntities);
    }

    @Override
    public Optional<Characteristic> getCharacteristicById(Integer id) {
        return characteristicCrudRepository.findById(id).map(characteristicMapper::toCharacteristic);
    }

    @Override
    public List<IGradeCharacteristicByRequirementId> getGradesCharacteristicByRequirementId(Integer requirementId) {
        return characteristicCrudRepository.findGradesByRequirementId(requirementId);
    }

    @Override
    public List<ICharacteristicsByRequirementId> getCharacteristicsByRequirementId(Integer requirementId) {
        return characteristicCrudRepository.findCharacteristicsByRequirementId(requirementId);
    }

    @Override
    public void updateCharacteristicByRequirementId(Integer requirementId, Integer characteristicId, String name, String description, String oppositeName, String oppositeDescription, Double gradeCharacteristic, boolean dde, boolean dii, boolean var) {
        characteristicCrudRepository.updateCharacteristicByRequirementId(requirementId, characteristicId, name, description, oppositeName, oppositeDescription, gradeCharacteristic, dde, dii, var);
    }

    @Override
    public void updateGradeCharacteristicByRequirement(Double gradeInput, Integer requirementId, Integer characteristicId) {
        characteristicCrudRepository.updateGradeCharacteristicByRequirement(gradeInput, requirementId, characteristicId);
    }

    @Override
    public void updateTypeErrorOfCharacteristic(boolean dde, boolean dii, boolean var, Integer requirementId, Integer characteristicId) {
        characteristicCrudRepository.updateTypeErrorOfCharacteristic(dde, dii, var, requirementId, characteristicId);
    }

    @Override
    public int countRequirementsByTypeAndNameCharacteristic(String typeRequirement, String nameCharacteristic) {
        return characteristicCrudRepository.countRequirementsByTypeAndNameCharacteristic(typeRequirement, nameCharacteristic);
    }
}
