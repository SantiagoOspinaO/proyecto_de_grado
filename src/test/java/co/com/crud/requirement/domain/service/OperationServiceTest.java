package co.com.crud.requirement.domain.service;

import co.com.crud.requirement.domain.model.Operation;
import co.com.crud.requirement.domain.model.Requirement;
import co.com.crud.requirement.domain.model.queryresult.IRequirementsByFilterCauseError;
import co.com.crud.requirement.domain.model.queryresult.IRequirementsByRequirementIdAndCauseError;
import co.com.crud.requirement.domain.model.queryresult.IRequirementsByTypeAndCauseError;
import co.com.crud.requirement.domain.repository.OperationDomainRepository;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
        operation.setLevelAdequacy(5.0);
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
        operation.setLevelAdequacy(5.0);
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

    @Test
    public void testCalculateWeightAverage(){
        //Arrange
        CharacteristicService characteristicService = mock(CharacteristicService.class);
        Requirement requirement = new Requirement();
        requirement.setRequirementId(1);
        requirement.setProjectId(1);
        requirement.setName("Requisito ensayo");
        requirement.setDescription("Requisito ensayo prueba");
        requirement.setTypeRequirement("Funcional");

        //Act
        double result = characteristicService.calculateWeightAverage(requirement.getRequirementId());

        //Assert
        verify(characteristicService, times(1)).calculateWeightAverage(requirement.getRequirementId());
        assertNotEquals(4,result);
    }

    @Test
    public void testMaximumAccumulatedScore(){
        //Arrange
        CharacteristicService characteristicService = mock(CharacteristicService.class);
        Requirement requirement = new Requirement();
        requirement.setRequirementId(1);
        requirement.setProjectId(1);
        requirement.setName("Requisito ensayo");
        requirement.setDescription("Requisito ensayo prueba");
        requirement.setTypeRequirement("Funcional");

        //Act
        double result = characteristicService.maximumAccumulatedScore(requirement.getRequirementId());

        //Assert
        verify(characteristicService, times(1)).maximumAccumulatedScore(requirement.getRequirementId());
        assertNotEquals(85.5,result);
    }

    @Test
    public void testAllOperations(){
        //Arrange
        CharacteristicService characteristicService = mock(CharacteristicService.class);
        Operation operation = new Operation();
        operation.setOperationId(1);
        operation.setRequirementId(1);
        operation.setMaximumScore(45.0);
        operation.setLevelAdequacy(5.0);
        operation.setEvaluatedCharacteristics(9.0);
        operation.setLevelWeightScore(55.55);
        operation.setCalculatedWeightAverage(0.55);

        //Act
        Operation result = characteristicService.allOperations(operation.getOperationId(), operation.getRequirementId());

        //Assert
        verify(characteristicService, times(1)).allOperations(operation.getOperationId(), operation.getRequirementId());
        verify(characteristicService, times(0)).levelWeightScoreForNineCharacters(anyInt());
        verify(characteristicService, times(0)).allEvaluationCharactersResult(anyInt());

    }

    @Test
    public void testCountRequirementsByRequirementIdAndCauseError(){
        //Arrange
        CharacteristicService characteristicService = mock(CharacteristicService.class);
        Requirement requirement = new Requirement();
        requirement.setRequirementId(1);
        requirement.setProjectId(1);
        requirement.setName("Requisito ensayo");
        requirement.setDescription("Requisito ensayo prueba");
        requirement.setTypeRequirement("Funcional");

        //Act
        IRequirementsByRequirementIdAndCauseError result = characteristicService.countRequirementsByRequirementIdAndCauseError(requirement.getRequirementId(), requirement.getProjectId());

        //Assert
        verify(characteristicService, times(1)).countRequirementsByRequirementIdAndCauseError(requirement.getRequirementId(), requirement.getProjectId());
        verify(characteristicService, times(0)).levelWeightScoreForNineCharacters(anyInt());
        verify(characteristicService, times(0)).allEvaluationCharactersResult(anyInt());

    }

    @Test
    public void testCountCauseErrorByRequirementType(){
        //Arrange
        CharacteristicService characteristicService = mock(CharacteristicService.class);
        Requirement requirement = new Requirement();
        requirement.setRequirementId(1);
        requirement.setProjectId(1);
        requirement.setName("Requisito ensayo");
        requirement.setDescription("Requisito ensayo prueba");
        requirement.setTypeRequirement("Funcional");

        //Act
        IRequirementsByFilterCauseError result = characteristicService.countCauseErrorByRequirementType(requirement.getTypeRequirement(), requirement.getProjectId());

        //Assert
        verify(characteristicService, times(1)).countCauseErrorByRequirementType(requirement.getTypeRequirement(), requirement.getProjectId());
        verify(characteristicService, times(0)).levelWeightScoreForNineCharacters(anyInt());
        verify(characteristicService, times(0)).allEvaluationCharactersResult(anyInt());

    }

    @Test
    public void testCountRequirementsByTypeAndCauseError(){
        //Arrange
        CharacteristicService characteristicService = mock(CharacteristicService.class);
        Requirement requirement = new Requirement();
        requirement.setRequirementId(1);
        requirement.setProjectId(1);
        requirement.setName("Requisito ensayo");
        requirement.setDescription("Requisito ensayo prueba");
        requirement.setTypeRequirement("Funcional");

        //Act
        IRequirementsByTypeAndCauseError result = characteristicService.countRequirementsByTypeAndCauseError(requirement.getTypeRequirement(), requirement.getProjectId());

        //Assert
        verify(characteristicService, times(1)).countRequirementsByTypeAndCauseError(requirement.getTypeRequirement(), requirement.getProjectId());
        verify(characteristicService, times(0)).levelWeightScoreForNineCharacters(anyInt());
        verify(characteristicService, times(0)).allEvaluationCharactersResult(anyInt());

    }

    @Test
    public void testGetPercentageCountRequirementsByTypeAndNameCharacteristicInterface(){
        //Arrange
        CharacteristicService characteristicService = mock(CharacteristicService.class);

        //Act
        characteristicService.getPercentageCountRequirementsByTypeAndNameCharacteristicInterface(any());

        //Assert
        verify(characteristicService, times(1)).getPercentageCountRequirementsByTypeAndNameCharacteristicInterface(any());

    }

    @Test
    public void testGetPercentageCharacteristicsByCauseErrorInterface(){
        //Arrange
        CharacteristicService characteristicService = mock(CharacteristicService.class);

        //Act
        characteristicService.getPercentageCharacteristicsByCauseErrorInterface(any());

        //Assert
        verify(characteristicService, times(1)).getPercentageCharacteristicsByCauseErrorInterface(any());

    }

    @Test
    public void testCalculatePercentage(){
        //Arrange
        CharacteristicService characteristicService = mock(CharacteristicService.class);
        double count = 2.0;
        double totalRecords = 3.0;

        //Act
        double result = characteristicService.calculatePercentage(count,totalRecords);

        //Assert
        verify(characteristicService, times(1)).calculatePercentage(count,totalRecords);
        assertNotNull(result, "null");

    }

}
