package co.com.crud.requirement.domain.service;

import co.com.crud.requirement.domain.exception.CharacteristicNotFoundException;
import co.com.crud.requirement.domain.exception.validation.RequirementAdecuationValidator;
import co.com.crud.requirement.domain.model.Characteristic;
import co.com.crud.requirement.domain.model.Operation;
import co.com.crud.requirement.domain.model.queryresult.*;
import co.com.crud.requirement.domain.repository.CharacteristicDomainRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public double calculateLevelAdequacy(Integer requirementId) {
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

    public void updateCharacteristicByRequirementId(Integer requirementId, Integer characteristicId, String name, String description, String oppositeName, String oppositeDescription, Double gradeCharacteristic, boolean dde, boolean dii, boolean var) {
        characteristicDomainRepository.updateCharacteristicByRequirementId(requirementId, characteristicId, name, description, oppositeName, oppositeDescription, gradeCharacteristic, dde, dii, var);
    }

    public void updateCauseErrorOfCharacteristic(boolean dde, boolean dii, boolean var, Integer requirementId, Integer characteristicId) {
        characteristicDomainRepository.updateCauseErrorOfCharacteristic(dde, dii, var, requirementId, characteristicId);
    }

    public double evaluatedCharacteristicForRequirement(Integer requirementId) {
        List<IGradeCharacteristicByRequirementId> grades = characteristicDomainRepository.getGradesCharacteristicByRequirementId(requirementId);
        int requirementEvaluated = 0;
        for (IGradeCharacteristicByRequirementId grade : grades) {
            requirementEvaluated = grades.size();
        }
        return requirementEvaluated;
    }

    public double calculateWeightAverage(Integer requirementId) {
        List<IGradeCharacteristicByRequirementId> grades = characteristicDomainRepository.getGradesCharacteristicByRequirementId(requirementId);
        double sumGrade = 0.0;
        for (IGradeCharacteristicByRequirementId grade : grades) {
            sumGrade += grade.getGrade();
        }
        double average = sumGrade / grades.size();
        return average / evaluatedCharacteristicForRequirement(requirementId);
    }

    public double maximumAccumulatedScore(Integer requirementId) {
        List<IGradeCharacteristicByRequirementId> grades = characteristicDomainRepository.getGradesCharacteristicByRequirementId(requirementId);
        double sumGrade = 0;
        double sum = 0;
        for (IGradeCharacteristicByRequirementId grade : grades) {
            sumGrade += grade.getGrade();
        }
        return sumGrade;
    }

    public double levelWeightScoreForNineCharacters(Integer requirementId) {
        double maxScore = maximumAccumulatedScore(requirementId);
        return ((maxScore / 81) * 100);
    }

    /*
    public List<Double> allOperations(Integer requirementId) {
        List<Double> resultados = new ArrayList<>();
        double levelAdequacy = calculateLevelAdequacy(requirementId);
        double evaluatedCharacteristics = evaluatedCharacteristicForRequirement(requirementId);
        double levelWeightScore = levelWeightScoreForNineCharacters(requirementId);
        double maximumScore = maximumAccumulatedScore(requirementId);
        double calculateWeightAverage = calculateWeightAverage(requirementId);
        resultados.add(levelAdequacy);
        resultados.add(evaluatedCharacteristics);
        resultados.add(levelWeightScore);
        resultados.add(maximumScore);
        resultados.add(calculateWeightAverage);
        return resultados;
    }*/

    public Operation allOperations(Integer operationId, Integer requirementId) {
        Operation operation = new Operation();
        operation.setOperationId(operationId);
        operation.setRequirementId(requirementId);
        operation.setLevelAdecuacy(calculateLevelAdequacy(requirementId));
        operation.setEvaluatedCharacteristics(evaluatedCharacteristicForRequirement(requirementId));
        operation.setLevelWeightScore(levelWeightScoreForNineCharacters(requirementId));
        operation.setMaximumScore(maximumAccumulatedScore(requirementId));
        operation.setCalculatedWeightAverage(calculateWeightAverage(requirementId));

        return operation;
    }

    public String allEvaluationCharactersResult(Integer requirementId) {
        double result = maximumAccumulatedScore(requirementId);
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

    public IRequirementsByTypeAndNameCharacteristic countRequirementsByTypeAndNameCharacteristic(String typeRequirement, Integer projectId) {
        return characteristicDomainRepository.countRequirementsByTypeAndNameCharacteristic(typeRequirement, projectId);
    }

    public IRequirementsByRequirementIdAndCauseError countRequirementsByRequirementIdAndCauseError(Integer requirementId, Integer projectId) {
        return characteristicDomainRepository.countRequirementsByRequirementIdAndCauseError(requirementId, projectId);
    }

    public IRequirementsByFilterCauseError countCauseErrorByRequirementType(String typeRequirement, Integer projectId) {
        return characteristicDomainRepository.countCauseErrorByRequirementType(typeRequirement, projectId);
    }

    public IRequirementsByTypeAndCauseError countRequirementsByTypeAndCauseError(String typeRequirement, Integer projectId) {
        return characteristicDomainRepository.countRequirementsByTypeAndCauseError(typeRequirement, projectId);
    }

    public double percentageOfNumberCharacteristics(String typeRequirement, Integer projectId) throws JsonProcessingException {
        IRequirementsByTypeAndNameCharacteristic allCharacteristicsByProjectId = characteristicDomainRepository.countRequirementsByTypeAndNameCharacteristic(typeRequirement, projectId);
        String jsonString = convertToJson(allCharacteristicsByProjectId);
        return sumValues(jsonString);
    }

    private String convertToJson(IRequirementsByTypeAndNameCharacteristic requirements) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.valueToTree(requirements);
        return objectMapper.writeValueAsString(jsonNode);
    }

    private double sumValues(String jsonString) throws JsonProcessingException {
        double sum = 0.0;
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(jsonString);

        for (JsonNode entry : jsonNode) {
            if (entry.isNumber()) {
                sum += entry.asDouble();
            }
        }

        return sum;
    }

}
