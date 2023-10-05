package co.com.crud.requirement.domain.service;

import co.com.crud.requirement.domain.exception.CharacteristicNotFoundException;
import co.com.crud.requirement.domain.exception.validation.RequirementAdecuationValidator;
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

    public double calculateLevelAdequacy() { // Calcular nivel de adecuacion ( sumar todas las notas y dividirlo por el total de las notas calificadas)
        List<IGradeOfCharacteristic> grades = characteristicDomainRepository.getGradesOfCharacteristics();
        double sumGrade = 0;
        for (IGradeOfCharacteristic grade : grades) {
            sumGrade += grade.getGrade();
        }
        return sumGrade / grades.size();
    }

    public double maximunAccumulatedScore() { // Suma todas las notas de la catacterisitca evaluada
        List<IGradeOfCharacteristic> grades = characteristicDomainRepository.getGradesOfCharacteristics();
        double sumGrade = 0;
        double sum = 0;
        for (IGradeOfCharacteristic grade : grades) {
            sumGrade += grade.getGrade();
        }
        return sumGrade;
    }

    public double levelWeightScoreForNineCharacters() { // Devuelve un porcentaje
        double maxScore = maximunAccumulatedScore();
        double result = ((maxScore / 81 ) * 100);
        return result;
    }

    public String allEvaluationCharactersResult() {
        double result = maximunAccumulatedScore();
        if (result > 72) {
            return RequirementAdecuationValidator.Adecuation_Alto_Alto;
        } else if (result < 72 && result > 63) {
            return RequirementAdecuationValidator.Adecuation_Alto_Medio;
        } else if (result < 63 && result > 54) {
            return RequirementAdecuationValidator.Adecuation_Alto_Bajo;
        } else if (result < 54 && result > 45) {
            return RequirementAdecuationValidator.Adecuation_Medio_Alto;
        } else if (result < 45 && result > 36) {
            return RequirementAdecuationValidator.Adecuation_Medio_Medio;
        } else if (result < 36 && result > 27) {
            return RequirementAdecuationValidator.Adecuation_Medio_Bajo;
        } else if (result < 27 && result > 18) {
            return RequirementAdecuationValidator.Adecuation_Bajo_Bajo;
        } else if (result < 18 && result > 9) {
            return RequirementAdecuationValidator.Adecuation_Bajo_Medio;
        } else if (result < 9) {
            return RequirementAdecuationValidator.Adecuation_Bajo_Bajo;
        }
        return null;
    }

    public List<ICharacteristicByRequirement> getCharacteristicByRequirement() {
        return characteristicDomainRepository.getCharacteristicByRequirement();
    }
}
