package co.com.crud.requirement.web.controller;

import co.com.crud.requirement.domain.model.Characteristic;
import co.com.crud.requirement.domain.model.Operation;
import co.com.crud.requirement.domain.model.queryresult.*;
import co.com.crud.requirement.domain.service.CharacteristicService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
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
    public Operation getAllOperations(@PathVariable("id") Integer operationId,@PathVariable("id") Integer requirementId) {
        return characteristicService.allOperations(operationId,requirementId);
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

    @GetMapping(path = "/algo")
    public double algo (@RequestParam String typeRequirement, @RequestParam Integer projectId) throws JsonProcessingException {
        return characteristicService.percentageOfNumberCharacteristics(typeRequirement, projectId);
    }

    @GetMapping(path = "/calculate-percentage-of-number-characteristics")
    public Map<String, Double> getPercentageCountRequirementsByTypeAndNameCharacteristic(
            @RequestParam(required = false) String typeRequirement,
            @RequestParam Integer projectId
    ) throws JsonProcessingException {

        IRequirementsByTypeAndNameCharacteristic requirements = characteristicService.countRequirementsByTypeAndNameCharacteristic(typeRequirement, projectId);

        double totalRecords = characteristicService.percentageOfNumberCharacteristics(typeRequirement, projectId);

        Double correcto = requirements.getCorrecto();
        Double incorrecto = requirements.getIncorrecto();
        Double inequivoco = requirements.getInequivoco();
        Double ambiguo = requirements.getAmbiguo();
        Double completo = requirements.getCompleto();
        Double incompleto = requirements.getIncompleto();
        Double consistente = requirements.getConsistente();
        Double debil = requirements.getDebil();
        Double importante = requirements.getImportante();
        Double intrascendente = requirements.getIntrascendente();
        Double estable = requirements.getEstable();
        Double inestable = requirements.getInestable();
        Double comprobable = requirements.getComprobable();
        Double nocomprobable = requirements.getNoComprobable();
        Double identificable = requirements.getIdentificable();
        Double noidentificable = requirements.getNoIdentificable();
        Double trazable = requirements.getTrazable();
        Double notrazable = requirements.getNoTrazable();

        Map<String, Double> result = new HashMap<>();
        result.put("correcto", calculatePercentage(correcto != null ? correcto : 0.0, totalRecords));
        result.put("incorrecto", calculatePercentage(incorrecto != null ? incorrecto : 0.0, totalRecords));
        result.put("inequivoco", calculatePercentage(inequivoco != null ? inequivoco : 0.0, totalRecords));
        result.put("ambiguo", calculatePercentage(ambiguo != null ? ambiguo : 0.0, totalRecords));
        result.put("completo(", calculatePercentage(completo != null ? completo : 0.0, totalRecords));
        result.put("incompleto", calculatePercentage(incompleto != null ? incompleto : 0.0, totalRecords));
        result.put("consistente", calculatePercentage(consistente != null ? consistente : 0.0, totalRecords));
        result.put("debil", calculatePercentage(debil != null ? debil : 0.0, totalRecords));
        result.put("importante", calculatePercentage(importante != null ? importante : 0.0, totalRecords));
        result.put("intrascendente", calculatePercentage(intrascendente != null ? intrascendente : 0.0, totalRecords));
        result.put("estable", calculatePercentage(estable != null ? estable : 0.0, totalRecords));
        result.put("inestable", calculatePercentage(inestable != null ? inestable : 0.0, totalRecords));
        result.put("comprobable", calculatePercentage(comprobable != null ? comprobable : 0.0, totalRecords));
        result.put("noComprobable", calculatePercentage(nocomprobable != null ? nocomprobable : 0.0, totalRecords));
        result.put("identificable", calculatePercentage(identificable != null ? identificable : 0.0, totalRecords));
        result.put("noIdentificable", calculatePercentage(noidentificable != null ? noidentificable : 0.0, totalRecords));
        result.put("trazable", calculatePercentage(trazable != null ? trazable : 0.0, totalRecords));
        result.put("noTrazable", calculatePercentage(notrazable != null ? notrazable : 0.0, totalRecords));

        return result;
    }

    private double calculatePercentage(double count, double totalRecords) {
        if (totalRecords == 0) {
            return 0.0;
        } else {
            return (count / totalRecords) * 100.0;
        }
    }

}