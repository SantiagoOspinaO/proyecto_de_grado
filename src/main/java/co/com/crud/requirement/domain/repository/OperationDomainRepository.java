package co.com.crud.requirement.domain.repository;

import co.com.crud.requirement.domain.model.Operation;
import co.com.crud.requirement.domain.model.queryresult.ITotalMaxScore;

import java.util.List;

public interface OperationDomainRepository {

    Operation saveOperation(Operation operation);

    List<Operation> getAllOperations();

    ITotalMaxScore countNumberScoreByProjectIdOrTypeRequirement(String typeRequirement, Integer projectId);

    ITotalMaxScore countAllScoreByProjectIdOrTypeRequirement(String typeRequirement, Integer projectId);

    void updateOperation(Double maximumScore, Double levelAdequacy, Double evaluatedCharacteristics,
                         Double levelWeightScore, Double calculatedWeightAverage, Integer requirementId);

}
