package co.com.crud.requirement.domain.service;

import co.com.crud.requirement.domain.exception.CharacteristicNotFoundException;
import co.com.crud.requirement.domain.exception.validation.RequirementAdecuationValidator;
import co.com.crud.requirement.domain.model.Characteristic;
import co.com.crud.requirement.domain.model.queryresult.ICharacteristicsByRequirementId;
import co.com.crud.requirement.domain.model.queryresult.IGradeCharacteristicByRequirementId;
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

    public double calculateLevelAdequacy(Integer requirementId) { // Calcular nivel de adecuacion ( sumar todas las notas y dividirlo por el total de las notas calificadas)
        List<IGradeCharacteristicByRequirementId> grades = characteristicDomainRepository.getGradesCharacteristicByRequirementId(requirementId);
        double sumGrade = 0;
        for (IGradeCharacteristicByRequirementId grade : grades) {
            sumGrade += grade.getGrade();
        }
        return sumGrade / grades.size();
    }

    public void updateGradeCharacteristicByRequirement(Double gradeInput, Integer requirementId, Integer characteristicId) {
        characteristicDomainRepository.updateGradeCharacteristicByRequirement(gradeInput, requirementId, characteristicId);
    }

    public void updateTypeErrorOfCharacteristic(boolean dde, boolean dii, boolean var, Integer requirementId, Integer characteristicId, Integer typeErrorId) {
        characteristicDomainRepository.updateTypeErrorOfCharacteristic(dde, dii, var, requirementId, characteristicId, typeErrorId);
    }

    public Integer evalutedCharacteristicForRequirement(Integer requirementId){ // Evalua todas las caracteristicas por requisito y devuelve el numero de caracteristicas evaluadas
        List<IGradeCharacteristicByRequirementId> grades = characteristicDomainRepository.getGradesCharacteristicByRequirementId(requirementId);
        int requirementEvaluated = 0;
        for (IGradeCharacteristicByRequirementId grade : grades) {
            requirementEvaluated = grades.size();
        }
        return requirementEvaluated;
    }

    public double calculateWeightAverage(Integer requirementId){
        List<IGradeCharacteristicByRequirementId> grades = characteristicDomainRepository.getGradesCharacteristicByRequirementId(requirementId);
        double sumGrade = 0.0;
        for (IGradeCharacteristicByRequirementId grade : grades) {
            sumGrade += grade.getGrade();
        }
        double average = sumGrade / grades.size();
        double weightAverage = average / evalutedCharacteristicForRequirement(requirementId);
        return weightAverage;
    }

    public double maximunAccumulatedScore(Integer requirementId) { // Suma todas las notas de la catacterisitca evaluada
        List<IGradeCharacteristicByRequirementId> grades = characteristicDomainRepository.getGradesCharacteristicByRequirementId(requirementId);
        double sumGrade = 0;
        double sum = 0;
        for (IGradeCharacteristicByRequirementId grade : grades) {
            sumGrade += grade.getGrade();
        }
        return sumGrade;
    }

    public double levelWeightScoreForNineCharacters(Integer requirementId) { // Devuelve un porcentaje
        double maxScore = maximunAccumulatedScore(requirementId);
        double result = ((maxScore / 81 ) * 100);
        return result;
    }
    /*
    public list<double> allOperations(Integer requirementId) {
        double levelAdecuacy = calculateLevelAdequacy(requirementId);
        double evaluatedCharacteristics = evalutedCharacteristicForRequirement(requirementId);
        double levelWeightScore = levelWeightScoreForNineCharacters(requirementId);
        double maximunScore = maximunAccumulatedScore(requirementId);
        String allEvaluation = allEvaluationCharactersResult(requirementId);


        return (levelAdecuacy, evaluatedCharacteristics, levelWeightScore, maximunScore, allEvaluation);

    }
    */
    public String allEvaluationCharactersResult(Integer requirementId) { //Devuelve un string de acuerdo a puntaje maximo acumulado
        double result = maximunAccumulatedScore(requirementId);
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

    public List<ICharacteristicsByRequirementId> getCharacteristicByRequirement(Integer requirementId) {
        return characteristicDomainRepository.getCharacteristicsByRequirementId(requirementId);
    }
}
