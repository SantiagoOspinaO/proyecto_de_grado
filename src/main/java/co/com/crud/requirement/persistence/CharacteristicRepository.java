package co.com.crud.requirement.persistence;

import co.com.crud.requirement.domain.model.Characteristic;
import co.com.crud.requirement.domain.model.queryresult.ICharacteristicByRequirement;
import co.com.crud.requirement.domain.model.queryresult.IGradeOfCharacteristic;
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
    public List<IGradeOfCharacteristic> getGradesOfCharacteristics() {
        return characteristicCrudRepository.findGradesOfCharacteristics();
    }

    @Override
    public void updateGradeOfCharacteristic(Integer requirementId, Integer characteristicId, Double gradeInput) {
        characteristicCrudRepository.updateGradeOfCharacteristic(requirementId, characteristicId, gradeInput);
    }

    @Override
    public List<ICharacteristicByRequirement> getCharacteristicByRequirement() {
        return characteristicCrudRepository.findCharacteristicsByRequirement();
    }
}
