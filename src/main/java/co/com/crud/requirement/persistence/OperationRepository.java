package co.com.crud.requirement.persistence;

import co.com.crud.requirement.domain.model.Operation;
import co.com.crud.requirement.domain.model.queryresult.ITotalMaxScore;
import co.com.crud.requirement.domain.repository.OperationDomainRepository;
import co.com.crud.requirement.persistence.crud.IOperationCrudRepository;
import co.com.crud.requirement.persistence.entity.OperationEntity;
import co.com.crud.requirement.persistence.mapper.OperationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OperationRepository implements OperationDomainRepository {

    private final IOperationCrudRepository operationCrudRepository;

    private final OperationMapper operationMapper;

    @Autowired
    public OperationRepository(IOperationCrudRepository operationCrudRepository, OperationMapper operationMapper) {
        this.operationCrudRepository = operationCrudRepository;
        this.operationMapper = operationMapper;
    }

    @Override
    public Operation saveOperation(Operation operation) {
        OperationEntity operationEntity = operationMapper.toOperations(operation);
        return operationMapper.toOperation(operationCrudRepository.save(operationEntity));
    }

    @Override
    public List<Operation> getAllOperations() {
        List<OperationEntity> operationEntities = (List<OperationEntity>) operationCrudRepository.findAll();
        return operationMapper.toOperationEntity(operationEntities);
    }

    @Override
    public ITotalMaxScore countNumberScoreByProjectIdOrTypeRequirement(String typeRequirement, Integer projectId) {
        return operationCrudRepository.countNumberScoreByProjectIdOrTypeRequirement(typeRequirement, projectId);
    }

    @Override
    public ITotalMaxScore countAllScoreByProjectIdOrTypeRequirement(String typeRequirement, Integer projectId) {
        return operationCrudRepository.countAllScoreByProjectIdOrTypeRequirement(typeRequirement, projectId);
    }

    @Override
    public void updateOperation(Double maximumScore, Double levelAdequacy, Double evaluatedCharacteristics, Double levelWeightScore, Double calculatedWeightAverage, Integer requirementId) {
        operationCrudRepository.updateOperation(maximumScore, levelAdequacy, evaluatedCharacteristics, levelWeightScore, calculatedWeightAverage, requirementId);
    }

}
