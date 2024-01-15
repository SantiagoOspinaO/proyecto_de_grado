package co.com.crud.requirement.domain.service;

import co.com.crud.requirement.domain.model.Operation;
import co.com.crud.requirement.domain.repository.OperationDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationService {

    private final OperationDomainRepository operationsDomainRepository;

    private final CharacteristicService characteristicService;

    @Autowired
    public OperationService(OperationDomainRepository operationsDomainRepository, CharacteristicService characteristicService) {
        this.operationsDomainRepository = operationsDomainRepository;
        this.characteristicService = characteristicService;
    }

    public Operation saveOperation(Operation operation) {
        Operation operationReturn = new Operation();
        operationReturn = characteristicService.allOperations(operation.getOperationId(), operation.getRequirementId());
        return operationsDomainRepository.saveOperation(operationReturn);
    }

    public List<Operation> getAllOperations() {
        return operationsDomainRepository.getAllOperations();
    }
}
