package co.com.crud.requirement.domain.repository;

import co.com.crud.requirement.domain.model.Operation;

import java.util.List;

public interface OperationDomainRepository {

    Operation saveOperation(Operation operation);

    List<Operation> getAllOperations();
}
