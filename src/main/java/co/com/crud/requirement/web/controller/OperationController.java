package co.com.crud.requirement.web.controller;

import co.com.crud.requirement.domain.model.AverageScore;
import co.com.crud.requirement.domain.model.Operation;
import co.com.crud.requirement.domain.model.queryresult.ITotalMaxScore;
import co.com.crud.requirement.domain.service.CharacteristicService;
import co.com.crud.requirement.domain.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static co.com.crud.requirement.web.constants.Constants.LOWER_RANGE_WEIGHT;
import static co.com.crud.requirement.web.constants.Constants.UPPER_RANGE_WEIGHT;

@CrossOrigin
@RestController
@RequestMapping("/operations")
public class OperationController {

    private final OperationService operationService;
    private final CharacteristicService characteristicService;

    @Autowired
    public OperationController(OperationService operationService, CharacteristicService characteristicService) {
        this.operationService = operationService;
        this.characteristicService = characteristicService;
    }

    @PutMapping(path = "/update-operation/{id}")
    public ResponseEntity<Operation> updateOperation(
            @PathVariable("id") Integer requirementId
    ) {
        int operationId = characteristicService.getOperationId(requirementId);
        Operation operation = characteristicService.allOperations(operationId, requirementId);

        operationService.updateOperation(
                operation.getMaximumScore(), operation.getLevelAdequacy(), operation.getEvaluatedCharacteristics(),
                operation.getLevelWeightScore(), operation.getCalculatedWeightAverage(), operation.getRequirementId());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping()
    public ResponseEntity<List<Operation>> getAllOperations() {
        return ResponseEntity.ok(operationService.getAllOperations());
    }

    @GetMapping(path = "/count-number-score-by-project-id-or-type-requirement")
    public ITotalMaxScore countNumberScoreByProjectIdOrTypeRequirement(
            @RequestParam(required = false) String typeRequirement,
            @RequestParam Integer projectId
    ) {
        return operationService.countNumberScoreByProjectIdOrTypeRequirement(typeRequirement, projectId);
    }

    @GetMapping(path = "/count-all-score-by-project-id-or-type-requirement")
    public ITotalMaxScore countAllScoreByProjectIdOrTypeRequirement(
            @RequestParam(required = false) String typeRequirement,
            @RequestParam Integer projectId
    ) {
        return operationService.countAllScoreByProjectIdOrTypeRequirement(typeRequirement, projectId);
    }

    @GetMapping(path = "/average-total-requirements-evaluated-by-level-adecuacy")
    public AverageScore averageTotalRequirementsEvaluatedByLevelAdequacy(
            @RequestParam(required = false) String typeRequirement,
            @RequestParam Integer projectId
    ) {
        return operationService.averageTotalRequirementsEvaluatedByLevelAdecuacy(typeRequirement, projectId);
    }

    @GetMapping(path = "/average-score-by-project-id-or-type-requirement")
    public AverageScore averageScoreByProjectIdOrTypeRequirement(
            @RequestParam(required = false) String typeRequirement,
            @RequestParam Integer projectId
    ) {
        return operationService.averageScoreByProjectIdOrTypeRequirement(typeRequirement, projectId);
    }

    @GetMapping(path = "/calculate-median")
    public AverageScore calculateWeightedMedian(
            @RequestParam String typeRequirement,
            @RequestParam Integer projectId
    ) {
        return operationService.calculateWeightedMedian(typeRequirement, projectId);
    }

    @GetMapping(path = "/weighted-average-upper-range")
    public AverageScore weightedAverageOfCumulativeScoreUpperRange(
            @RequestParam String typeRequirement,
            @RequestParam Integer projectId
    ) {
        return operationService.weightedAverageOfCumulativeScore(typeRequirement, projectId, UPPER_RANGE_WEIGHT);
    }

    @GetMapping(path = "/weighted-average-lower-range")
    public AverageScore weightedAverageOfCumulativeScoreLowerRange(
            @RequestParam String typeRequirement,
            @RequestParam Integer projectId
    ) {
        return operationService.weightedAverageOfCumulativeScore(typeRequirement, projectId, LOWER_RANGE_WEIGHT);
    }

    @GetMapping(path = "/total-weighted-median")
    public double totalWeightedMedianSuitabilityLevel(
            @RequestParam String typeRequirement,
            @RequestParam Integer projectId,
            @RequestParam Integer graphicNumber
    ) {
        return operationService.totalWeightedMedianSuitabilityLevel(typeRequirement, projectId, graphicNumber);
    }

}
