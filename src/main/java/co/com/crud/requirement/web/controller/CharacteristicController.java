package co.com.crud.requirement.web.controller;

import co.com.crud.requirement.domain.model.Characteristic;
import co.com.crud.requirement.domain.model.Operation;
import co.com.crud.requirement.domain.model.queryresult.*;
import co.com.crud.requirement.domain.service.CharacteristicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(path = "/update-cause-error/{requirementId}/{characteristicId}")
    public ResponseEntity<Void> updateCauseErrorOfCharacteristic(
            @PathVariable Integer requirementId,
            @PathVariable Integer characteristicId,
            @RequestBody Map<String, Boolean> causeErrorData
    ) {
        boolean dde = causeErrorData.get("dde");
        boolean dii = causeErrorData.get("dii");
        boolean ceVAR = causeErrorData.get("var");

        characteristicService.updateCauseErrorOfCharacteristic(dde, dii, ceVAR, requirementId, characteristicId);
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
        return characteristicService.allEvaluationCharacteristicsResult(requirementId);
    }

    @GetMapping(path = "/calculate-weight-average/{id}")
    public double calculateWeightAverage(@PathVariable("id") Integer requirementId) {
        return characteristicService.calculateWeightAverage(requirementId);
    }

    @GetMapping(path = "/characteristics-requirement/{id}")
    public List<ICharacteristicsByRequirementId> getCharacteristicByRequirement(@PathVariable("id") Integer requirementId) {
        return characteristicService.getCharacteristicByRequirement(requirementId);
    }

    @GetMapping(path = "/all-operations/{id}")
    public Operation getAllOperations(@PathVariable("id") Integer requirementId) {
        int operationId = characteristicService.getOperationId(requirementId);
        return characteristicService.allOperations(operationId, requirementId);
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

    @GetMapping(path = "/count-type-requirement-name-characteristic")
    public IRequirementsByTypeAndNameCharacteristic countRequirementsByTypeAndNameCharacteristic(
            @RequestParam(required = false) String typeRequirement,
            @RequestParam Integer projectId
    ) {
        return characteristicService.countRequirementsByTypeAndNameCharacteristic(typeRequirement, projectId);
    }

    @GetMapping(path = "/percentage-type-requirement-name-characteristic")
    public Map<String, Double> percentageRequirementsByTypeAndNameCharacteristic(
            @RequestParam(required = false) String typeRequirement,
            @RequestParam Integer projectId
    ) {
        IRequirementsByTypeAndNameCharacteristic requirements = countRequirementsByTypeAndNameCharacteristic(typeRequirement, projectId);
        return characteristicService.getPercCountRequirementsByTypeAndNameChar(requirements);
    }

    @GetMapping(path = "/count-characteristics-cause-error-dde")
    public ICharacteristicsByCauseError countCharacteristicsByCauseErrorDDE(
            @RequestParam String typeRequirement,
            @RequestParam Integer projectId
    ) {
        return characteristicService.countCharacteristicsByCauseErrorDDE(typeRequirement, projectId);
    }

    @GetMapping(path = "/percentage-characteristics-cause-error-dde")
    public Map<String, Double> percentageCharacteristicsByCauseErrorDDE(
            @RequestParam String typeRequirement,
            @RequestParam Integer projectId
    ) {
        ICharacteristicsByCauseError characteristicDDE = countCharacteristicsByCauseErrorDDE(typeRequirement, projectId);
        return characteristicService.getPercentageCharacteristicsByCauseErrorInterface(characteristicDDE);
    }

    @GetMapping(path = "/count-characteristics-cause-error-dii")
    public ICharacteristicsByCauseError countCharacteristicsByCauseErrorDII(
            @RequestParam String typeRequirement,
            @RequestParam Integer projectId
    ) {
        return characteristicService.countCharacteristicsByCauseErrorDII(typeRequirement, projectId);
    }

    @GetMapping(path = "/percentage-characteristics-cause-error-dii")
    public Map<String, Double> percentageCharacteristicsByCauseErrorDII(
            @RequestParam String typeRequirement,
            @RequestParam Integer projectId
    ) {
        ICharacteristicsByCauseError characteristicDII = countCharacteristicsByCauseErrorDII(typeRequirement, projectId);
        return characteristicService.getPercentageCharacteristicsByCauseErrorInterface(characteristicDII);
    }

    @GetMapping(path = "/count-characteristics-cause-error-var")
    public ICharacteristicsByCauseError countCharacteristicsByCauseErrorVAR(
            @RequestParam String typeRequirement,
            @RequestParam Integer projectId
    ) {
        return characteristicService.countCharacteristicsByCauseErrorVAR(typeRequirement, projectId);
    }

    @GetMapping(path = "/percentage-characteristics-cause-error-var")
    public Map<String, Double> percentageCharacteristicsByCauseErrorVAR(
            @RequestParam String typeRequirement,
            @RequestParam Integer projectId
    ) {
        ICharacteristicsByCauseError characteristicVAR = countCharacteristicsByCauseErrorVAR(typeRequirement, projectId);
        return characteristicService.getPercentageCharacteristicsByCauseErrorInterface(characteristicVAR);
    }

}