package co.com.crud.requirement.persistence;

import co.com.crud.requirement.domain.model.Characteristic;
import co.com.crud.requirement.domain.model.queryresult.*;
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
    public void updateGradeCharacteristicByRequirement(Double gradeInput, Integer requirementId, Integer characteristicId) {
        characteristicCrudRepository.updateGradeCharacteristicByRequirement(gradeInput, requirementId, characteristicId);
    }

    @Override
    public void updateCauseErrorOfCharacteristic(boolean dde, boolean dii, boolean CEvar, Integer requirementId, Integer characteristicId) {
        characteristicCrudRepository.updateCauseErrorOfCharacteristic(dde, dii, CEvar, requirementId, characteristicId);
    }

    @Override
    public IRequirementsByTypeAndNameCharacteristic countRequirementsByTypeAndNameCharacteristic(String typeRequirement, Integer projectId) {
        return characteristicCrudRepository.countRequirementsByTypeAndNameCharacteristic(typeRequirement, projectId);
    }

    @Override
    public IRequirementsByRequirementIdAndCauseError countRequirementsByRequirementIdAndCauseError(Integer requirementId, Integer projectId) {
        return characteristicCrudRepository.countRequirementsByRequirementIdAndCauseError(requirementId, projectId);
    }

    @Override
    public IRequirementsByFilterCauseError countCauseErrorByRequirementType(String typeRequirement, Integer projectId) {
        return characteristicCrudRepository.countCauseErrorByRequirementType(typeRequirement, projectId);
    }

    @Override
    public IRequirementsByTypeAndCauseError countRequirementsByTypeAndCauseError(String typeRequirement, Integer projectId) {
        return characteristicCrudRepository.countRequirementsByTypeAndCauseError(typeRequirement, projectId);
    }

    @Override
    public ICharacteristicsByCauseError countCharacteristicsByCauseErrorDDE(String typeRequirement, Integer projectId) {
        return characteristicCrudRepository.countCharacteristicsByCauseErrorDDE(typeRequirement, projectId);
    }

    @Override
    public ICharacteristicsByCauseError countCharacteristicsByCauseErrorDII(String typeRequirement, Integer projectId) {
        return characteristicCrudRepository.countCharacteristicsByCauseErrorDII(typeRequirement, projectId);
    }

    @Override
    public ICharacteristicsByCauseError countCharacteristicsByCauseErrorVAR(String typeRequirement, Integer projectId) {
        return characteristicCrudRepository.countCharacteristicsByCauseErrorVAR(typeRequirement, projectId);
    }

}
