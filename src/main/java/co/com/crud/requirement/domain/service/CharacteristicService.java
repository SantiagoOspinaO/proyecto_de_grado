package co.com.crud.requirement.domain.service;

import co.com.crud.requirement.domain.exception.CharacteristicNotFoundException;
import co.com.crud.requirement.domain.exception.validation.RequirementAdecuationValidator;
import co.com.crud.requirement.domain.model.Characteristic;
import co.com.crud.requirement.domain.model.Operation;
import co.com.crud.requirement.domain.model.queryresult.*;
import co.com.crud.requirement.domain.repository.CharacteristicDomainRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public void updateCauseErrorOfCharacteristic(boolean dde, boolean dii, boolean CEvar, Integer requirementId, Integer characteristicId) {
        characteristicDomainRepository.updateCauseErrorOfCharacteristic(dde, dii, CEvar, requirementId, characteristicId);
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
        for (IGradeCharacteristicByRequirementId grade : grades) {
            sumGrade += grade.getGrade();
        }
        return sumGrade;
    }

    public double levelWeightScoreForNineCharacters(Integer requirementId) {
        double maxScore = maximumAccumulatedScore(requirementId);
        return ((maxScore / 81) * 100);
    }

    public Operation allOperations(Integer operationId, Integer requirementId) {
        Operation operation = new Operation();
        operation.setOperationId(operationId);
        operation.setRequirementId(requirementId);
        operation.setLevelAdequacy(calculateLevelAdequacy(requirementId));
        operation.setEvaluatedCharacteristics(evaluatedCharacteristicForRequirement(requirementId));
        operation.setLevelWeightScore(levelWeightScoreForNineCharacters(requirementId));
        operation.setMaximumScore(maximumAccumulatedScore(requirementId));
        operation.setCalculatedWeightAverage(calculateWeightAverage(requirementId));
        return operation;
    }

    public String allEvaluationCharsResult(Integer requirementId) {
        double result = maximumAccumulatedScore(requirementId);
        if (result > 72.09) {
            return RequirementAdecuationValidator.Adecuation_Alto_Alto;
        } else if (result <= 72.09 && result > 63.09) {
            return RequirementAdecuationValidator.Adecuation_Alto_Medio;
        } else if (result <= 63.09 && result > 54.09) {
            return RequirementAdecuationValidator.Adecuation_Alto_Bajo;
        } else if (result <= 54.09 && result > 45.09) {
            return RequirementAdecuationValidator.Adecuation_Medio_Alto;
        } else if (result <= 45.09 && result > 36.09) {
            return RequirementAdecuationValidator.Adecuation_Medio_Medio;
        } else if (result <= 36.09 && result > 27.09) {
            return RequirementAdecuationValidator.Adecuation_Medio_Bajo;
        } else if (result <= 27.09 && result > 18.09) {
            return RequirementAdecuationValidator.Adecuation_Bajo_Bajo;
        } else if (result <= 18.09 && result > 9.09) {
            return RequirementAdecuationValidator.Adecuation_Bajo_Medio;
        } else if (result <= 9.09) {
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

    public ICharacteristicsByCauseError countCharacteristicsByCauseErrorDDE(String typeRequirement, Integer projectId) {
        return characteristicDomainRepository.countCharacteristicsByCauseErrorDDE(typeRequirement, projectId);
    }

    public ICharacteristicsByCauseError countCharacteristicsByCauseErrorDII(String typeRequirement, Integer projectId) {
        return characteristicDomainRepository.countCharacteristicsByCauseErrorDII(typeRequirement, projectId);
    }

    public ICharacteristicsByCauseError countCharacteristicsByCauseErrorVAR(String typeRequirement, Integer projectId) {
        return characteristicDomainRepository.countCharacteristicsByCauseErrorVAR(typeRequirement, projectId);
    }

    @NotNull
    public Map<String, Double> getPercCountRequirementsByTypeAndNameChar(IRequirementsByTypeAndNameCharacteristic requirements) {
        double correcto = requirements.getCorrecto() != null ? requirements.getCorrecto() : 0.0;
        double incorrecto = requirements.getIncorrecto() != null ? requirements.getIncorrecto() : 0.0;
        double inequivoco = requirements.getInequivoco() != null ? requirements.getInequivoco() : 0.0;
        double ambiguo = requirements.getAmbiguo() != null ? requirements.getAmbiguo() : 0.0;
        double completo = requirements.getCompleto() != null ? requirements.getCompleto() : 0.0;
        double incompleto = requirements.getIncompleto() != null ? requirements.getIncompleto() : 0.0;
        double consistente = requirements.getConsistente() != null ? requirements.getConsistente() : 0.0;
        double debil = requirements.getDebil() != null ? requirements.getDebil() : 0.0;
        double importante = requirements.getImportante() != null ? requirements.getImportante() : 0.0;
        double intrascendente = requirements.getIntrascendente() != null ? requirements.getIntrascendente() : 0.0;
        double estable = requirements.getEstable() != null ? requirements.getEstable() : 0.0;
        double inestable = requirements.getInestable() != null ? requirements.getInestable() : 0.0;
        double comprobable = requirements.getComprobable() != null ? requirements.getComprobable() : 0.0;
        double noComprobable = requirements.getNoComprobable() != null ? requirements.getNoComprobable() : 0.0;
        double identificable = requirements.getIdentificable() != null ? requirements.getCorrecto() : 0.0;
        double noIdentificable = requirements.getNoIdentificable() != null ? requirements.getNoIdentificable() : 0.0;
        double trazable = requirements.getTrazable() != null ? requirements.getTrazable() : 0.0;
        double noTrazable = requirements.getNoTrazable() != null ? requirements.getNoTrazable() : 0.0;

        double totalPerfect = correcto + inequivoco + completo + consistente + importante + estable + comprobable + identificable + trazable;
        double totalImperfect = incorrecto + ambiguo + incompleto + debil + intrascendente + inestable + noComprobable + noIdentificable + noTrazable;

        Map<String, Double> result = new HashMap<>();
        result.put("correcto", calculatePercentage(correcto, totalPerfect));
        result.put("incorrecto", calculatePercentage(incorrecto, totalImperfect));
        result.put("inequivoco", calculatePercentage(inequivoco, totalPerfect));
        result.put("ambiguo", calculatePercentage(ambiguo, totalImperfect));
        result.put("completo", calculatePercentage(completo, totalPerfect));
        result.put("incompleto", calculatePercentage(incompleto, totalImperfect));
        result.put("consistente", calculatePercentage(consistente, totalPerfect));
        result.put("debil", calculatePercentage(debil, totalImperfect));
        result.put("importante", calculatePercentage(importante, totalPerfect));
        result.put("intrascendente", calculatePercentage(intrascendente, totalImperfect));
        result.put("estable", calculatePercentage(estable, totalPerfect));
        result.put("inestable", calculatePercentage(inestable, totalImperfect));
        result.put("comprobable", calculatePercentage(comprobable, totalPerfect));
        result.put("noComprobable", calculatePercentage(noComprobable, totalImperfect));
        result.put("identificable", calculatePercentage(identificable, totalPerfect));
        result.put("noIdentificable", calculatePercentage(noIdentificable, totalImperfect));
        result.put("trazable", calculatePercentage(trazable, totalPerfect));
        result.put("noTrazable", calculatePercentage(noTrazable, totalImperfect));

        return result;
    }

    @NotNull
    public Map<String, Double> getPercentageCharacteristicsByCauseErrorInterface(ICharacteristicsByCauseError characteristics) {
        double incorrecto = characteristics.getIncorrecto() != null ? characteristics.getIncorrecto() : 0.0;
        double ambiguo = characteristics.getAmbiguo() != null ? characteristics.getAmbiguo() : 0.0;
        double incompleto = characteristics.getIncompleto() != null ? characteristics.getIncompleto() : 0.0;
        double debil = characteristics.getDebil() != null ? characteristics.getDebil() : 0.0;
        double intrascendente = characteristics.getIntrascendente() != null ? characteristics.getIntrascendente() : 0.0;
        double inestable = characteristics.getInestable() != null ? characteristics.getInestable() : 0.0;
        double noComprobable = characteristics.getNoComprobable() != null ? characteristics.getNoComprobable() : 0.0;
        double noIdentificable = characteristics.getNoIdentificable() != null ? characteristics.getNoIdentificable() : 0.0;
        double noTrazable = characteristics.getNoTrazable() != null ? characteristics.getNoTrazable() : 0.0;

        double totalImperfect = incorrecto + ambiguo + incompleto + debil + intrascendente + inestable + noComprobable + noIdentificable + noTrazable;

        Map<String, Double> result = new HashMap<>();
        result.put("incorrecto", calculatePercentage(incorrecto, totalImperfect));
        result.put("ambiguo", calculatePercentage(ambiguo, totalImperfect));
        result.put("incompleto", calculatePercentage(incompleto, totalImperfect));
        result.put("debil", calculatePercentage(debil, totalImperfect));
        result.put("intrascendente", calculatePercentage(intrascendente, totalImperfect));
        result.put("inestable", calculatePercentage(inestable, totalImperfect));
        result.put("noComprobable", calculatePercentage(noComprobable, totalImperfect));
        result.put("noIdentificable", calculatePercentage(noIdentificable, totalImperfect));
        result.put("noTrazable", calculatePercentage(noTrazable, totalImperfect));

        return result;
    }

    public double calculatePercentage(double count, double totalRecords) {
        if (totalRecords == 0) {
            return 0.0;
        } else {
            double percentage = (count / totalRecords) * 100.0;
            String formattedPercentage = String.format("%.2f", percentage).replace(",", ".");
            return Double.parseDouble(formattedPercentage);
        }
    }

}
