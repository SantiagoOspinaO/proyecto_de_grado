package co.com.crud.requirement.persistence;

import co.com.crud.requirement.domain.model.Characteristic;
import co.com.crud.requirement.domain.model.queryresult.ICharacteristicByRequirement;
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

    private final ICharacteristicCrudRepository ICharacteristicCrudRepository;

    private final CharacteristicMapper characteristicMapper;

    @Autowired
    public CharacteristicRepository(ICharacteristicCrudRepository ICharacteristicCrudRepository, CharacteristicMapper characteristicMapper) {
        this.ICharacteristicCrudRepository = ICharacteristicCrudRepository;
        this.characteristicMapper = characteristicMapper;
    }

    @Override
    public List<Characteristic> getAllCharacteristics() {
       List<CharacteristicEntity> characterEntities = (List<CharacteristicEntity>) ICharacteristicCrudRepository.findAll();
       return characteristicMapper.toCharacteristicsEntity(characterEntities);
    }

    @Override
    public Optional<Characteristic> getCharacteristicById(Integer id) {
        return ICharacteristicCrudRepository.findById(id).map(characteristicMapper::toCharacteristic);
    }

    @Override
    public List<ICharacteristicByRequirement> getAllGrades() {
        return ICharacteristicCrudRepository.findCharacteristicsByRequirement();
    }

    @Override
    public List<ICharacteristicByRequirement> getCharacteristicByRequirement() {
        return null;
    }
}
