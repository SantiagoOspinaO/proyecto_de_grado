package co.com.crud.requirement.domain.service;

import co.com.crud.requirement.domain.exception.CharacteristicNotFoundException;
import co.com.crud.requirement.domain.exception.validation.RequirementAdecuationValidator;
import co.com.crud.requirement.domain.model.Characteristic;
import co.com.crud.requirement.domain.model.queryresult.ICharacteristicsByRequirementId;
import co.com.crud.requirement.domain.model.queryresult.IGradeCharacteristicByRequirementId;
import co.com.crud.requirement.domain.repository.CharacteristicDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Characteristic service.
 */
@Service
public class CharacteristicService {

    private final CharacteristicDomainRepository characteristicDomainRepository;

    /**
     * Instantiates a new Characteristic service.
     *
     * @param characteristicDomainRepository the characteristic domain repository
     */
    @Autowired
    public CharacteristicService(CharacteristicDomainRepository characteristicDomainRepository) {
        this.characteristicDomainRepository = characteristicDomainRepository;
    }

    /**
     * Gets all characteristics.
     *
     * @return the all characteristics
     */
    public List<Characteristic> getAllCharacteristics() {
        return characteristicDomainRepository.getAllCharacteristics();
    }

    /**
     * Gets characteristic by id.
     *
     * @param id the id
     * @return the characteristic by id
     */
    public Optional<Characteristic> getCharacteristicById(Integer id) {
        Optional<Characteristic> characteristics = characteristicDomainRepository.getCharacteristicById(id);
        if (characteristics.isEmpty()) {
            throw new CharacteristicNotFoundException(id);
        }
        return characteristics;
    }

    /**
     * Calcular nivel de adecuacion ( sumar todas las notas y dividirlo por el total de las notas calificadas)
     *
     * @param requirementId the requirement id
     * @return the double
     */
    public double calculateLevelAdequacy(Integer requirementId) {
        List<IGradeCharacteristicByRequirementId> grades = characteristicDomainRepository.getGradesCharacteristicByRequirementId(requirementId);
        double sumGrade = 0;
        for (IGradeCharacteristicByRequirementId grade : grades) {
            sumGrade += grade.getGrade();
        }
        return sumGrade / grades.size();
    }

    /**
     * Update grade characteristic by requirement.
     *
     * @param gradeInput       the grade input
     * @param requirementId    the requirement id
     * @param characteristicId the characteristic id
     */
    public void updateGradeCharacteristicByRequirement(Double gradeInput, Integer requirementId, Integer characteristicId) {
        characteristicDomainRepository.updateGradeCharacteristicByRequirement(gradeInput, requirementId, characteristicId);
    }

    /**
     * Update characteristic by requirement id.
     *
     * @param requirementId       the requirement id
     * @param name                the name
     * @param description         the description
     * @param oppositeName        the opposite name
     * @param oppositeDescription the opposite description
     * @param gradeCharacteristic the grade characteristic
     * @param dde                 the dde
     * @param dii                 the dii
     * @param var                 the var
     */
    public void updateCharacteristicByRequirementId(Integer requirementId, Integer characteristicId, String name, String description, String oppositeName, String oppositeDescription, Double gradeCharacteristic, boolean dde, boolean dii, boolean var) {
        characteristicDomainRepository.updateCharacteristicByRequirementId(requirementId, characteristicId, name, description, oppositeName, oppositeDescription, gradeCharacteristic, dde, dii, var);
    }

    /**
     * Update type error of characteristic.
     *
     * @param dde              the dde
     * @param dii              the dii
     * @param var              the var
     * @param requirementId    the requirement id
     * @param characteristicId the characteristic id
     */
    public void updateTypeErrorOfCharacteristic(boolean dde, boolean dii, boolean var, Integer requirementId, Integer characteristicId) {
        characteristicDomainRepository.updateTypeErrorOfCharacteristic(dde, dii, var, requirementId, characteristicId);
    }

    /**
     * Evalua todas las caracteristicas por requisito y devuelve el numero de caracteristicas evaluadas
     *
     * @param requirementId the requirement id
     * @return the integer
     */
    public Integer evalutedCharacteristicForRequirement(Integer requirementId) {
        List<IGradeCharacteristicByRequirementId> grades = characteristicDomainRepository.getGradesCharacteristicByRequirementId(requirementId);
        int requirementEvaluated = 0;
        for (IGradeCharacteristicByRequirementId grade : grades) {
            requirementEvaluated = grades.size();
        }
        return requirementEvaluated;
    }

    /**
     * Calculate weight average double.
     *
     * @param requirementId the requirement id
     * @return the double
     */
    public double calculateWeightAverage(Integer requirementId) {
        List<IGradeCharacteristicByRequirementId> grades = characteristicDomainRepository.getGradesCharacteristicByRequirementId(requirementId);
        double sumGrade = 0.0;
        for (IGradeCharacteristicByRequirementId grade : grades) {
            sumGrade += grade.getGrade();
        }
        double average = sumGrade / grades.size();
        return average / evalutedCharacteristicForRequirement(requirementId);
    }

    /**
     * Suma todas las notas de la catacterisitca evaluada
     *
     * @param requirementId the requirement id
     * @return the double
     */
    public double maximunAccumulatedScore(Integer requirementId) {
        List<IGradeCharacteristicByRequirementId> grades = characteristicDomainRepository.getGradesCharacteristicByRequirementId(requirementId);
        double sumGrade = 0;
        double sum = 0;
        for (IGradeCharacteristicByRequirementId grade : grades) {
            sumGrade += grade.getGrade();
        }
        return sumGrade;
    }

    /**
     * Devuelve un porcentaje
     *
     * @param requirementId the requirement id
     * @return the double
     */
    public double levelWeightScoreForNineCharacters(Integer requirementId) {
        double maxScore = maximunAccumulatedScore(requirementId);
        return ((maxScore / 81) * 100);
    }

    /**
     * All operations list.
     *
     * @param requirementId the requirement id
     * @return the list
     */
    public List<Double> allOperations(Integer requirementId) {
        List<Double> resultados = new ArrayList<>();
        double levelAdecuacy = calculateLevelAdequacy(requirementId);
        double evaluatedCharacteristics = evalutedCharacteristicForRequirement(requirementId);
        double levelWeightScore = levelWeightScoreForNineCharacters(requirementId);
        double maximunScore = maximunAccumulatedScore(requirementId);
        double calculateWeightAverage = calculateWeightAverage(requirementId);
        resultados.add(levelAdecuacy);
        resultados.add(evaluatedCharacteristics);
        resultados.add(levelWeightScore);
        resultados.add(maximunScore);
        resultados.add(calculateWeightAverage);
        return resultados;
    }

    /**
     * Devuelve un string de acuerdo a puntaje maximo acumulado
     *
     * @param requirementId the requirement id
     * @return the string
     */
    public String allEvaluationCharactersResult(Integer requirementId) {
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

    /**
     * Gets characteristic by requirement.
     *
     * @param requirementId the requirement id
     * @return the characteristic by requirement
     */
    public List<ICharacteristicsByRequirementId> getCharacteristicByRequirement(Integer requirementId) {
        return characteristicDomainRepository.getCharacteristicsByRequirementId(requirementId);
    }
}
