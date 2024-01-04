package co.com.crud.requirement.web.controller;

import co.com.crud.requirement.domain.model.Characteristic;
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
        return characteristicService.evalutedCharacteristicForRequirement(requirementId);
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
        return characteristicService.maximunAccumulatedScore(requirementId);
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
    public List<Double> getAllOperations(@PathVariable("id") Integer requirementId) {
        return characteristicService.allOperations(requirementId);
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
    public IRequirementsByFilterCauseError countCauseErrorByRequirementType(@RequestParam(required = false) String typeRequirement) {
        return characteristicService.countCauseErrorByRequirementType(typeRequirement);
    }

    @GetMapping(path = "/count-type-requirement-cause-error")
    public IRequirementsByTypeAndCauseError countRequirementsByTypeAndCauseError(
            @RequestParam String typeRequirement,
            @RequestParam Integer projectId
    ) {
        return characteristicService.countRequirementsByTypeAndCauseError(typeRequirement, projectId);
    }

}
