package co.com.crud.requirement.domain.service;

import co.com.crud.requirement.domain.model.Operation;
import co.com.crud.requirement.domain.repository.CharacteristicDomainRepository;
import co.com.crud.requirement.domain.repository.OperationDomainRepository;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OperationServiceTest {

    @MockBean
    OperationDomainRepository operationDomainRepository;

    @Test
    public void testGetAllOperationsWhenResultIsNotNull() {
        //Arrange
        OperationDomainRepository mockOperationDomainRepository = mock(OperationDomainRepository.class);
        CharacteristicService characteristicService = mock(CharacteristicService.class);
        RequirementService requirementService = mock(RequirementService.class);
        Operation operation = new Operation();
        operation.setOperationId(1);
        operation.setRequirementId(1);
        operation.setLevelAdecuacy(5.0);
        operation.setEvaluatedCharacteristics(9.0);
        operation.setLevelWeightScore(55.555);
        operation.setMaximumScore(45.0);
        operation.setCalculatedWeightAverage(0.555556);

        ArrayList<Operation> mockOperations = new ArrayList<Operation>();
        mockOperations.add(operation);
        when(mockOperationDomainRepository.getAllOperations()).thenReturn(mockOperations);
        OperationService operationService = new OperationService(mockOperationDomainRepository,characteristicService, requirementService);

        //Act
        List<Operation> result = operationService.getAllOperations();

        //Assert
        verify(mockOperationDomainRepository, times(1)).getAllOperations();
        assertNotNull(result);

    }

    @Test
    public void testSaveOperation() {
        //Arrange
        OperationDomainRepository operationDomainRepository = mock(OperationDomainRepository.class);
        CharacteristicService characteristicService = mock(CharacteristicService.class);
        RequirementService requirementService = mock(RequirementService.class);
        OperationService operationService = new OperationService(operationDomainRepository,characteristicService, requirementService);
        Operation operation = new Operation();
        operation.setOperationId(1);
        operation.setRequirementId(1);
        operation.setLevelAdecuacy(5.0);
        operation.setEvaluatedCharacteristics(9.0);
        operation.setLevelWeightScore(55.555);
        operation.setMaximumScore(45.0);
        operation.setCalculatedWeightAverage(0.555556);

        ArrayList<Operation> mockOperations = new ArrayList<Operation>();
        mockOperations.add(operation);
        when(operationDomainRepository.saveOperation(any())).thenReturn(operation);
        when(characteristicService.allOperations(any(),any())).thenReturn(operation);
        //Act
        Operation result = operationService.saveOperation(operation);

        //Assert
        verify(operationDomainRepository, times(1)).saveOperation(operation);
        assertEquals(operation, result);
    }
}
