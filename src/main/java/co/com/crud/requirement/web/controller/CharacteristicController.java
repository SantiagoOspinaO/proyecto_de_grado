package co.com.crud.requirement.web.controller;

import co.com.crud.requirement.domain.model.Characteristic;
import co.com.crud.requirement.domain.model.Operation;
import co.com.crud.requirement.domain.model.queryresult.*;
import co.com.crud.requirement.domain.service.CharacteristicService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/characteristics")
public class CharacteristicController {

    private final CharacteristicService characteristicService;

    @Autowired
    public CharacteristicController(CharacteristicService characteristicService) {
        this.characteristicService = characteristicService;
    }

    @GetMapping()
    public ResponseEntity<List<Characteristic>> getAllCharacteristics() {
        return ResponseEntity.ok(characteristicService.getAllCharacteristics());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Characteristic>> getCharacteristicById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(characteristicService.getCharacteristicById(id));
    }

    @GetMapping(path = "/level-adequacy/{id}")
    public double calculateLevelAdequacy(@PathVariable("id") Integer requirementId) {
        return characteristicService.calculateLevelAdequacy(requirementId);
    }

    @GetMapping(path = "/evaluated-characteristic-for-requirement/{id}")
    public double calculateEvaluatedCharacteristicForRequirement(@PathVariable("id") Integer requirementId) {
        return characteristicService.evaluatedCharacteristicForRequirement(requirementId);
    }

    @PostMapping(path = "/update-grade/{requirementId}/{characteristicId}")
    public ResponseEntity<Void> updateGradeCharacteristicByRequirement(
            @RequestBody Double gradeInput,
            @PathVariable Integer requirementId,
            @PathVariable Integer characteristicId
    ) {
        characteristicService.updateGradeCharacteristicByRequirement(gradeInput, requirementId, characteristicId);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/update-characteristic-requirement/{requirementId}/{characteristicId}")
    public ResponseEntity<Void> updateCharacteristicByRequirementId(
            @PathVariable Integer requirementId,
            @PathVariable Integer characteristicId,
            @RequestBody Map<String, Object> requestData
    ) {
        String name = (String) requestData.get("name");
        String description = (String) requestData.get("description");
        String oppositeName = (String) requestData.get("oppositeName");
        String oppositeDescription = (String) requestData.get("oppositeDescription");
        Double gradeCharacteristic = (Double) requestData.get("gradeCharacteristic");
        Boolean dde = (Boolean) requestData.get("dde");
        Boolean dii = (Boolean) requestData.get("dii");
        Boolean var = (Boolean) requestData.get("var");

        characteristicService.updateCharacteristicByRequirementId(requirementId, characteristicId, name, description, oppositeName, oppositeDescription, gradeCharacteristic, dde, dii, var);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/update-cause-error/{requirementId}/{characteristicId}")
    public ResponseEntity<Void> updateCauseErrorOfCharacteristic(
            @PathVariable Integer requirementId,
            @PathVariable Integer characteristicId,
            @RequestBody Map<String, Boolean> causeErrorData
    ) {
        boolean dde = causeErrorData.get("dde");
        boolean dii = causeErrorData.get("dii");
        boolean var = causeErrorData.get("var");

        characteristicService.updateCauseErrorOfCharacteristic(dde, dii, var, requirementId, characteristicId);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/maximum-accumulated-score/{id}")
    public double calculateMaximumAccumulatedScore(@PathVariable("id") Integer requirementId) {
        return characteristicService.maximumAccumulatedScore(requirementId);
    }

    @GetMapping(path = "/level-weight-score/{id}")
    public double calculateLevelWeightScoreForNineCharacters(@PathVariable("id") Integer requirementId) {
        return characteristicService.levelWeightScoreForNineCharacters(requirementId);
    }

    @GetMapping(path = "/all-evaluation-characters-result/{id}")
    public String calculateAllEvaluationCharactersResult(@PathVariable("id") Integer requirementId) {
        return characteristicService.allEvaluationCharactersResult(requirementId);
    }

    @GetMapping(path = "/calculate-weight-average/{id}")
    public double calculateWeightAverage(@PathVariable("id") Integer requirementId) {
        return characteristicService.calculateWeightAverage(requirementId);
    }

    @GetMapping(path = "characteristics-requirement/{id}")
    public List<ICharacteristicsByRequirementId> getCharacteristicByRequirement(@PathVariable("id") Integer requirementId) {
        return characteristicService.getCharacteristicByRequirement(requirementId);
    }

    @GetMapping(path = "all-operations/{id}")
    public Operation getAllOperations(@PathVariable("id") Integer operationId, @PathVariable("id") Integer requirementId) {
        return characteristicService.allOperations(operationId, requirementId);
    }

    @GetMapping(path = "/count-type-requirement-name-characteristic")
    public IRequirementsByTypeAndNameCharacteristic countRequirementsByTypeAndNameCharacteristic(
            @RequestParam(required = false) String typeRequirement,
            @RequestParam Integer projectId
    ) {
        return characteristicService.countRequirementsByTypeAndNameCharacteristic(typeRequirement, projectId);
    }

    @GetMapping(path = "/count-requirement-id-cause-error")
    public IRequirementsByRequirementIdAndCauseError countRequirementsByRequirementIdAndCauseError(
            @RequestParam Integer requirementId,
            @RequestParam Integer projectId
    ) {
        return characteristicService.countRequirementsByRequirementIdAndCauseError(requirementId, projectId);
    }

    @GetMapping(path = "/count-cause-error-requirement-type")
    public IRequirementsByFilterCauseError countCauseErrorByRequirementType(
            @RequestParam(required = false) String typeRequirement,
            @RequestParam Integer projectId
    ) {
        return characteristicService.countCauseErrorByRequirementType(typeRequirement, projectId);
    }

    @GetMapping(path = "/count-type-requirement-cause-error")
    public IRequirementsByTypeAndCauseError countRequirementsByTypeAndCauseError(
            @RequestParam(required = false) String typeRequirement,
            @RequestParam Integer projectId
    ) {
        return characteristicService.countRequirementsByTypeAndCauseError(typeRequirement, projectId);
    }

    @GetMapping(path = "/calculate-percentage-of-name-characteristics")
    public Map<String, Double> getPercentageCountRequirementsByTypeAndNameCharacteristic(
            @RequestParam(required = false) String typeRequirement,
            @RequestParam Integer projectId
    ) {
        IRequirementsByTypeAndNameCharacteristic requirements = characteristicService.countRequirementsByTypeAndNameCharacteristic(typeRequirement, projectId);

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
        double nocomprobable = requirements.getNoComprobable() != null ? requirements.getNoComprobable() : 0.0;
        double identificable = requirements.getIdentificable() != null ? requirements.getCorrecto() : 0.0;
        double noidentificable = requirements.getNoIdentificable() != null ? requirements.getNoIdentificable() : 0.0;
        double trazable = requirements.getTrazable() != null ? requirements.getTrazable() : 0.0;
        double notrazable = requirements.getNoTrazable() != null ? requirements.getNoTrazable() : 0.0;

        double totalPerfect = correcto + inequivoco + completo + consistente + importante + estable + comprobable + identificable + trazable;
        double totalImperfect = incorrecto + ambiguo + incompleto + debil + intrascendente + inestable + nocomprobable + noidentificable + notrazable;

        Map<String, Double> result = new HashMap<>();
        result.put("correcto", characteristicService.calculatePercentage(correcto, totalPerfect));
        result.put("incorrecto", characteristicService.calculatePercentage(incorrecto, totalImperfect));
        result.put("inequivoco", characteristicService.calculatePercentage(inequivoco, totalPerfect));
        result.put("ambiguo", characteristicService.calculatePercentage(ambiguo, totalImperfect));
        result.put("completo", characteristicService.calculatePercentage(completo, totalPerfect));
        result.put("incompleto", characteristicService.calculatePercentage(incompleto, totalImperfect));
        result.put("consistente", characteristicService.calculatePercentage(consistente, totalPerfect));
        result.put("debil", characteristicService.calculatePercentage(debil, totalImperfect));
        result.put("importante", characteristicService.calculatePercentage(importante, totalPerfect));
        result.put("intrascendente", characteristicService.calculatePercentage(intrascendente, totalImperfect));
        result.put("estable", characteristicService.calculatePercentage(estable, totalPerfect));
        result.put("inestable", characteristicService.calculatePercentage(inestable, totalImperfect));
        result.put("comprobable", characteristicService.calculatePercentage(comprobable, totalPerfect));
        result.put("noComprobable", characteristicService.calculatePercentage(nocomprobable, totalImperfect));
        result.put("identificable", characteristicService.calculatePercentage(identificable, totalPerfect));
        result.put("noIdentificable", characteristicService.calculatePercentage(noidentificable, totalImperfect));
        result.put("trazable", characteristicService.calculatePercentage(trazable, totalPerfect));
        result.put("noTrazable", characteristicService.calculatePercentage(notrazable, totalImperfect));

        return result;
    }



    @GetMapping(path = "/count-characteristics-cause-error-dde")
    public ICharacteristicsByCauseError countCharacteristicsByCauseErrorDDE(
            @RequestParam String typeRequirement,
            @RequestParam Integer projectId
    ) {
        return characteristicService.countCharacteristicsByCauseErrorDDE(typeRequirement, projectId);
    }

    @GetMapping(path = "/calculate-percentage-characteristics-cause-error-dde")
    public Map<String, Double> getPercentageCountCharacteristicsByCauseErrorDDE(
            @RequestParam String typeRequirement,
            @RequestParam Integer projectId
    ) {
        ICharacteristicsByCauseError characteristicDDE = characteristicService.countCharacteristicsByCauseErrorDDE(typeRequirement, projectId);
        return characteristicService.getStringDoubleMap(characteristicDDE);
    }

    @GetMapping(path = "/count-characteristics-cause-error-dii")
    public ICharacteristicsByCauseError countCharacteristicsByCauseErrorDII(
            @RequestParam String typeRequirement,
            @RequestParam Integer projectId
    ) {
        return characteristicService.countCharacteristicsByCauseErrorDII(typeRequirement, projectId);
    }

    @GetMapping(path = "/calculate-percentage-characteristics-cause-error-dii")
    public Map<String, Double> getPercentageCountCharacteristicsByCauseErrorDII(
            @RequestParam String typeRequirement,
            @RequestParam Integer projectId
    ) {
        ICharacteristicsByCauseError characteristicDII = characteristicService.countCharacteristicsByCauseErrorDII(typeRequirement, projectId);
        return characteristicService.getStringDoubleMap(characteristicDII);
    }

    @GetMapping(path = "/count-characteristics-cause-error-var")
    public ICharacteristicsByCauseError countCharacteristicsByCauseErrorVAR(
            @RequestParam String typeRequirement,
            @RequestParam Integer projectId
    ) {
        return characteristicService.countCharacteristicsByCauseErrorVAR(typeRequirement, projectId);
    }

    @GetMapping(path = "/calculate-percentage-characteristics-cause-error-var")
    public Map<String, Double> getPercentageCountCharacteristicsByCauseErrorVAR(
            @RequestParam String typeRequirement,
            @RequestParam Integer projectId
    ) {
        ICharacteristicsByCauseError characteristicVAR = characteristicService.countCharacteristicsByCauseErrorVAR(typeRequirement, projectId);
        return characteristicService.getStringDoubleMap(characteristicVAR);
    }

}