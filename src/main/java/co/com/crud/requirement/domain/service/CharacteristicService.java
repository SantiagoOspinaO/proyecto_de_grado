package co.com.crud.requirement.domain.service;

import co.com.crud.requirement.domain.exception.CharacteristicNotFoundException;
import co.com.crud.requirement.domain.model.Characteristic;
import co.com.crud.requirement.domain.model.queryresult.ICharacteristicByRequirement;
import co.com.crud.requirement.domain.model.queryresult.IGradeOfCharacteristic;
import co.com.crud.requirement.domain.repository.CharacteristicDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacteristicService {

    private final CharacteristicDomainRepository characteristicDomainRepository;

    @Autowired
    public CharacteristicService(CharacteristicDomainRepository characteristicDomainRepository) {
        this.characteristicDomainRepository = characteristicDomainRepository;
    }

    public List<Characteristic> getAllCharacteristics() {
        return characteristicDomainRepository.getAllCharacteristics();
    }

    public Optional<Characteristic> getCharacteristicById(Integer id) {
        Optional<Characteristic> characteristics = characteristicDomainRepository.getCharacteristicById(id);
        if (characteristics.isEmpty()) {
            throw new CharacteristicNotFoundException(id);
        }
        return characteristics;
    }

    public double calculateLevelAdequacy() {
        List<IGradeOfCharacteristic> grades = characteristicDomainRepository.getGradesOfCharacteristics();
        double sumGrade = 0;
        for (IGradeOfCharacteristic grade : grades) {
            sumGrade += grade.getGrade();
        }
        return sumGrade / grades.size();
    }

    public List<ICharacteristicByRequirement> getCharacteristicByRequirement() {
        return characteristicDomainRepository.getCharacteristicByRequirement();
    }
}
