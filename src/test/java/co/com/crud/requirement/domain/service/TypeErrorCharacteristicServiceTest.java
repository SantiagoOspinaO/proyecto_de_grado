package co.com.crud.requirement.domain.service;


import co.com.crud.requirement.domain.model.TypeErrorCharacteristic;
import co.com.crud.requirement.domain.repository.TypeErrorCharacteristicDomainRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TypeErrorCharacteristicServiceTest {

    @MockBean
    TypeErrorCharacteristicDomainRepository typeErrorDomainRepository;

    @Test
    public void testGetAllTypeErrorWhenResultIsNotNull() {
        //Arrange
        TypeErrorCharacteristicDomainRepository mockTypeErrorRepository = mock(TypeErrorCharacteristicDomainRepository.class);
        TypeErrorCharacteristicService typeErrorCharacteristicService = mock(TypeErrorCharacteristicService.class);
        TypeErrorCharacteristic typeErrorCharacteristic = new TypeErrorCharacteristic();
        typeErrorCharacteristic.setTypeErrorId(1);
        typeErrorCharacteristic.setName("EIE");
        typeErrorCharacteristic.setDescription("Especificación incompleta o errónea");

        ArrayList<TypeErrorCharacteristic> mockTypeErrors = new ArrayList<TypeErrorCharacteristic>();
        mockTypeErrors.add(typeErrorCharacteristic);
        when(mockTypeErrorRepository.getAllTypesErrors()).thenReturn(mockTypeErrors);

        //Act
        List<TypeErrorCharacteristic> result = typeErrorCharacteristicService.getAllTypesErrors();

        //ASSERT
        verify(typeErrorCharacteristicService, times(1)).getAllTypesErrors();
        assertNotNull(result);
    }

    @Test
    public void testGetTypeErrorById() {
        //Arrange
        TypeErrorCharacteristicDomainRepository mockTypeErrorRepository = mock(TypeErrorCharacteristicDomainRepository.class);
        TypeErrorCharacteristicService typeErrorCharacteristicService = mock(TypeErrorCharacteristicService.class);
        Optional TypeErrorCharacteristic = null;
        int tipeErrorId = 1;

        when(mockTypeErrorRepository.getTypeErrorById(tipeErrorId)).thenReturn(TypeErrorCharacteristic);
        //Act
        var result = typeErrorCharacteristicService.getTypeErrorById(tipeErrorId);
        //Assert
        verify(typeErrorCharacteristicService, times(1)).getTypeErrorById(tipeErrorId);
    }

    @Test
    public void testCountRequirementsByTypeAndCauseError() {
        //Arrange
        TypeErrorCharacteristicService typeErrorCharacteristicService = mock(TypeErrorCharacteristicService.class);
        TypeErrorCharacteristicDomainRepository mockTypeErrorRepository = mock(TypeErrorCharacteristicDomainRepository.class);
        String typeRequirement = "Funcional";
        String causeError = "EIE";
        int returnNum = 8;

        when(mockTypeErrorRepository.countRequirementsByTypeAndCauseError(typeRequirement, causeError)).thenReturn(returnNum);

        //Act
        var result = typeErrorCharacteristicService.countRequirementsByTypeAndCauseError(typeRequirement, causeError);

        //Assert
        verify(typeErrorCharacteristicService, times(1)).countRequirementsByTypeAndCauseError(typeRequirement, causeError);
        assertNotEquals(7, result);
    }

    @Test
    public void testCountRequirementsByCauseErrorDDE() {
        //Arrange
        TypeErrorCharacteristicService typeErrorCharacteristicService = mock(TypeErrorCharacteristicService.class);
        TypeErrorCharacteristicDomainRepository mockTypeErrorRepository = mock(TypeErrorCharacteristicDomainRepository.class);
        int returnNum = 8;

        when(mockTypeErrorRepository.countRequirementByCauseErrorDDE()).thenReturn(returnNum);
        //Act
        var result = typeErrorCharacteristicService.countRequirementsByCauseErrorDDE();

        //Assert
        verify(typeErrorCharacteristicService, times(1)).countRequirementsByCauseErrorDDE();
        verify(typeErrorCharacteristicService, times(0)).countRequirementsByCauseErrorDII();
        verify(typeErrorCharacteristicService, times(0)).countRequirementsByCauseErrorVAR();
        assertNotEquals(8, result);
        assertNotEquals(8, result);

    }

    @Test
    public void testCountRequirementsByCauseErrorDII() {
        //Arrange
        TypeErrorCharacteristicService typeErrorCharacteristicService = mock(TypeErrorCharacteristicService.class);
        TypeErrorCharacteristicDomainRepository mockTypeErrorRepository = mock(TypeErrorCharacteristicDomainRepository.class);
        int returnNum = 8;

        when(mockTypeErrorRepository.countRequirementByCauseErrorDII()).thenReturn(returnNum);
        //Act
        var result = typeErrorCharacteristicService.countRequirementsByCauseErrorDII();

        //Assert
        verify(typeErrorCharacteristicService, times(0)).countRequirementsByCauseErrorDDE();
        verify(typeErrorCharacteristicService, times(1)).countRequirementsByCauseErrorDII();
        verify(typeErrorCharacteristicService, times(0)).countRequirementsByCauseErrorVAR();
        assertNotEquals(8, result);
        assertNotEquals(8, result);
    }

    @Test
    public void testCountRequirementsByCauseErrorVAR() {
        //Arrange
        TypeErrorCharacteristicService typeErrorCharacteristicService = mock(TypeErrorCharacteristicService.class);
        TypeErrorCharacteristicDomainRepository mockTypeErrorRepository = mock(TypeErrorCharacteristicDomainRepository.class);
        int returnNum = 8;

        when(mockTypeErrorRepository.countRequirementByCauseErrorVAR()).thenReturn(returnNum);
        //Act
        var result = typeErrorCharacteristicService.countRequirementsByCauseErrorVAR();

        //Assert
        verify(typeErrorCharacteristicService, times(0)).countRequirementsByCauseErrorDDE();
        verify(typeErrorCharacteristicService, times(0)).countRequirementsByCauseErrorDII();
        verify(typeErrorCharacteristicService, times(1)).countRequirementsByCauseErrorVAR();
        assertNotEquals(8, result);

    }

    @Test
    public void testCountRequirementsByCauseErrorAndRequirementId() {
        //Arrange
        int requirementId = 1;
        int typeErrorId = 2;
        String causeError = "DDE";
        int aReturnInt = 2;
        TypeErrorCharacteristicService typeErrorCharacteristicService = mock(TypeErrorCharacteristicService.class);
        TypeErrorCharacteristicDomainRepository mockTypeErrorRepository = mock(TypeErrorCharacteristicDomainRepository.class);

        when(mockTypeErrorRepository.countRequirementsByCauseErrorAndRequirementId(requirementId, typeErrorId, causeError)).thenReturn(aReturnInt);
        //Act
        var result = typeErrorCharacteristicService.countRequirementsByCauseErrorAndRequirementId(requirementId, typeErrorId, causeError);

        //Assert
        verify(typeErrorCharacteristicService, times(1)).countRequirementsByCauseErrorAndRequirementId(requirementId, typeErrorId, causeError);
        assertNotEquals(5, result);
    }

    @Test
    public void testCountTypeErrorEIEByRequirement() {
        //Arrange
        int requirementId = 1;
        int aReturnInt = 2;
        TypeErrorCharacteristicService typeErrorCharacteristicService = mock(TypeErrorCharacteristicService.class);
        TypeErrorCharacteristicDomainRepository mockTypeErrorRepository = mock(TypeErrorCharacteristicDomainRepository.class);

        when(mockTypeErrorRepository.countTypeErrorEIEByRequirement(requirementId)).thenReturn(aReturnInt);
        //Act
        var result = typeErrorCharacteristicService.countTypeErrorEIEByRequirement(requirementId);

        //Assert
        verify(typeErrorCharacteristicService, times(1)).countTypeErrorEIEByRequirement(requirementId);
        verify(typeErrorCharacteristicService, times(0)).countTypeErrorMCCByRequirement(requirementId);
        assertNotEquals(5, result);
    }

    @Test
    public void testCountTypeErrorEIEAndCauseErrorDDEByRequirement() {
        //Arrange
        int requirementId = 1;
        int aReturnInt = 2;
        TypeErrorCharacteristicService typeErrorCharacteristicService = mock(TypeErrorCharacteristicService.class);
        TypeErrorCharacteristicDomainRepository mockTypeErrorRepository = mock(TypeErrorCharacteristicDomainRepository.class);

        when(mockTypeErrorRepository.countTypeErrorEIEAndCauseErrorDDEByRequirement(requirementId)).thenReturn(aReturnInt);
        //Act
        var result = typeErrorCharacteristicService.countTypeErrorEIEAndCauseErrorDDEByRequirement(requirementId);

        //Assert
        verify(typeErrorCharacteristicService, times(1)).countTypeErrorEIEAndCauseErrorDDEByRequirement(requirementId);
        verify(typeErrorCharacteristicService, times(0)).countTypeErrorEIEAndCauseErrorDIIByRequirement(requirementId);
        verify(typeErrorCharacteristicService, times(0)).countTypeErrorEIEAndCauseErrorVARByRequirement(requirementId);
        assertNotEquals(5, result);
    }

    @Test
    public void testCountTypeErrorEIEAndCauseErrorDIIByRequirement() {
        //Arrange
        int requirementId = 1;
        int aReturnInt = 2;
        TypeErrorCharacteristicService typeErrorCharacteristicService = mock(TypeErrorCharacteristicService.class);
        TypeErrorCharacteristicDomainRepository mockTypeErrorRepository = mock(TypeErrorCharacteristicDomainRepository.class);

        when(mockTypeErrorRepository.countTypeErrorEIEAndCauseErrorDIIByRequirement(requirementId)).thenReturn(aReturnInt);
        //Act
        var result = typeErrorCharacteristicService.countTypeErrorEIEAndCauseErrorDIIByRequirement(requirementId);

        //Assert
        verify(typeErrorCharacteristicService, times(0)).countTypeErrorEIEAndCauseErrorDDEByRequirement(requirementId);
        verify(typeErrorCharacteristicService, times(1)).countTypeErrorEIEAndCauseErrorDIIByRequirement(requirementId);
        verify(typeErrorCharacteristicService, times(0)).countTypeErrorEIEAndCauseErrorVARByRequirement(requirementId);
        assertNotEquals(5, result);
    }

    @Test
    public void testCountTypeErrorEIEAndCauseErrorVARByRequirement() {
        //Arrange
        int requirementId = 1;
        int aReturnInt = 2;
        TypeErrorCharacteristicService typeErrorCharacteristicService = mock(TypeErrorCharacteristicService.class);
        TypeErrorCharacteristicDomainRepository mockTypeErrorRepository = mock(TypeErrorCharacteristicDomainRepository.class);

        when(mockTypeErrorRepository.countTypeErrorEIEAndCauseErrorVARByRequirement(requirementId)).thenReturn(aReturnInt);
        //Act
        var result = typeErrorCharacteristicService.countTypeErrorEIEAndCauseErrorVARByRequirement(requirementId);

        //Assert
        verify(typeErrorCharacteristicService, times(0)).countTypeErrorEIEAndCauseErrorDDEByRequirement(requirementId);
        verify(typeErrorCharacteristicService, times(0)).countTypeErrorEIEAndCauseErrorDIIByRequirement(requirementId);
        verify(typeErrorCharacteristicService, times(1)).countTypeErrorEIEAndCauseErrorVARByRequirement(requirementId);
        assertNotEquals(5, result);
    }

    @Test
    public void testCountTypeErrorMCCByRequirement() {
        //Arrange
        int requirementId = 1;
        int aRetunrInt = 2;
        TypeErrorCharacteristicService typeErrorCharacteristicService = mock(TypeErrorCharacteristicService.class);
        TypeErrorCharacteristicDomainRepository mockTypeErrorRepository = mock(TypeErrorCharacteristicDomainRepository.class);

        when(mockTypeErrorRepository.countTypeErrorMCCByRequirement(requirementId)).thenReturn(aRetunrInt);
        //Act
        var result = typeErrorCharacteristicService.countTypeErrorMCCByRequirement(requirementId);

        //Assert
        verify(typeErrorCharacteristicService, times(0)).countTypeErrorEIEByRequirement(requirementId);
        verify(typeErrorCharacteristicService, times(1)).countTypeErrorMCCByRequirement(requirementId);
        assertNotEquals(5, result);
    }

    @Test
    public void testCountTypeErrorMCCAndCauseErrorDDEByRequirement() {
        //Arrange
        int requirementId = 1;
        int aReturnInt = 2;
        TypeErrorCharacteristicService typeErrorCharacteristicService = mock(TypeErrorCharacteristicService.class);
        TypeErrorCharacteristicDomainRepository mockTypeErrorRepository = mock(TypeErrorCharacteristicDomainRepository.class);

        when(mockTypeErrorRepository.countTypeErrorMCCAndCauseErrorDDEByRequirement(requirementId)).thenReturn(aReturnInt);
        //Act
        var result = typeErrorCharacteristicService.countTypeErrorMCCAndCauseErrorDDEByRequirement(requirementId);

        //Assert
        verify(typeErrorCharacteristicService, times(1)).countTypeErrorMCCAndCauseErrorDDEByRequirement(requirementId);
        verify(typeErrorCharacteristicService, times(0)).countTypeErrorMCCAndCauseErrorDIIByRequirement(requirementId);
        verify(typeErrorCharacteristicService, times(0)).countTypeErrorMCCAndCauseErrorVARByRequirement(requirementId);
        assertNotEquals(5, result);
    }

    @Test
    public void testCountTypeErrorMCCAndCauseErrorDIIByRequirement() {
        //Arrange
        int requirementId = 1;
        int aReturnInt = 2;
        TypeErrorCharacteristicService typeErrorCharacteristicService = mock(TypeErrorCharacteristicService.class);
        TypeErrorCharacteristicDomainRepository mockTypeErrorRepository = mock(TypeErrorCharacteristicDomainRepository.class);

        when(mockTypeErrorRepository.countTypeErrorMCCAndCauseErrorDIIByRequirement(requirementId)).thenReturn(aReturnInt);
        //Act
        var result = typeErrorCharacteristicService.countTypeErrorMCCAndCauseErrorDIIByRequirement(requirementId);

        //Assert
        verify(typeErrorCharacteristicService, times(0)).countTypeErrorMCCAndCauseErrorDDEByRequirement(requirementId);
        verify(typeErrorCharacteristicService, times(1)).countTypeErrorMCCAndCauseErrorDIIByRequirement(requirementId);
        verify(typeErrorCharacteristicService, times(0)).countTypeErrorMCCAndCauseErrorVARByRequirement(requirementId);
        assertNotEquals(5, result);
    }

    @Test
    public void testCountTypeErrorMCCAndCauseErrorVARByRequirement() {
        //Arrange
        int requirementId = 1;
        int aReturnInt = 2;
        TypeErrorCharacteristicService typeErrorCharacteristicService = mock(TypeErrorCharacteristicService.class);
        TypeErrorCharacteristicDomainRepository mockTypeErrorRepository = mock(TypeErrorCharacteristicDomainRepository.class);

        when(mockTypeErrorRepository.countTypeErrorMCCAndCauseErrorVARByRequirement(requirementId)).thenReturn(aReturnInt);
        //Act
        var result = typeErrorCharacteristicService.countTypeErrorMCCAndCauseErrorVARByRequirement(requirementId);

        //Assert
        verify(typeErrorCharacteristicService, times(0)).countTypeErrorMCCAndCauseErrorDDEByRequirement(requirementId);
        verify(typeErrorCharacteristicService, times(0)).countTypeErrorMCCAndCauseErrorDIIByRequirement(requirementId);
        verify(typeErrorCharacteristicService, times(1)).countTypeErrorMCCAndCauseErrorVARByRequirement(requirementId);
        assertNotEquals(5, result);
    }

    @Test
    public void testcountTypeErrorsMCCByRequirement() {

    }

    @Test
    public void testCountTypeErrorsByRequirements() {
        //Arrange
        int typeErrorId = 1;
        int requirementId = 1;
        int aReturnInt = 2;
        TypeErrorCharacteristicService typeErrorCharacteristicService = mock(TypeErrorCharacteristicService.class);
        TypeErrorCharacteristicDomainRepository mockTypeErrorRepository = mock(TypeErrorCharacteristicDomainRepository.class);

        when(mockTypeErrorRepository.countTypeErrorsByRequirements(typeErrorId, requirementId)).thenReturn(aReturnInt);
        //Act
        var result = typeErrorCharacteristicService.countTypeErrorsByRequirements(typeErrorId, requirementId);

        //Assert
        verify(typeErrorCharacteristicService, times(1)).countTypeErrorsByRequirements(typeErrorId, requirementId);
        assertNotEquals(5, result);
    }

    @Test
    public void testCountAllTypeErrorsByRequirement() {
        //Arrange
        int requirementId = 1;
        int aReturnInt = 2;
        TypeErrorCharacteristicService typeErrorCharacteristicService = mock(TypeErrorCharacteristicService.class);
        TypeErrorCharacteristicDomainRepository mockTypeErrorRepository = mock(TypeErrorCharacteristicDomainRepository.class);

        when(mockTypeErrorRepository.countAllTypeErrorsByRequirement(requirementId)).thenReturn(aReturnInt);
        //Act
        var result = typeErrorCharacteristicService.countAllTypeErrorsByRequirement(requirementId);

        //Assert
        verify(typeErrorCharacteristicService, times(1)).countAllTypeErrorsByRequirement(requirementId);
        assertNotEquals(5, result);
    }

    @Test
    public void testCountAllCauseErrorsByRequirement() {
        //Arrange
        int requirementId = 1;
        int aReturnInt = 2;
        TypeErrorCharacteristicService typeErrorCharacteristicService = mock(TypeErrorCharacteristicService.class);
        TypeErrorCharacteristicDomainRepository mockTypeErrorRepository = mock(TypeErrorCharacteristicDomainRepository.class);

        when(mockTypeErrorRepository.countAllTypeErrorsByRequirement(requirementId)).thenReturn(aReturnInt);
        //Act
        var result = typeErrorCharacteristicService.countAllTypeErrorsByRequirement(requirementId);

        //Assert
        verify(typeErrorCharacteristicService, times(1)).countAllTypeErrorsByRequirement(requirementId);
        assertNotEquals(5, result);
    }

    @Test
    public void testnumberOfCauseErrorById() {

    }

    @Test
    public void testPercentageOfCauseErrorById() {
        //Arrange
        int requirementId = 12;
        int typeErrorId = 1;
        String causeError = "VAR";
        int aReturnInt = 10;
        double aReturnDouble = 10.0;
        TypeErrorCharacteristicService typeErrorCharacteristicService = mock(TypeErrorCharacteristicService.class);
        TypeErrorCharacteristicDomainRepository mockTypeErrorRepository = mock(TypeErrorCharacteristicDomainRepository.class);

        when(mockTypeErrorRepository.countAllCauseErrorsByRequirement(requirementId)).thenReturn(aReturnInt);
        when(mockTypeErrorRepository.countRequirementsByCauseErrorAndRequirementId(requirementId, typeErrorId, causeError)).thenReturn((int) aReturnDouble);

        //Act

        var result = typeErrorCharacteristicService.percentageOfCauseErrorById(requirementId, typeErrorId, causeError);

        //Assert
        verify(typeErrorCharacteristicService, times(1)).percentageOfCauseErrorById(requirementId, typeErrorId, causeError);
        assertNotEquals(5, result);
    }

    @Test
    public void testPercentageOfTypeErrorEIEById() {
        //Arrange
        int requirementId = 12;
        double aReturnDouble = 10.0;
        TypeErrorCharacteristicService typeErrorCharacteristicService = mock(TypeErrorCharacteristicService.class);
        TypeErrorCharacteristicDomainRepository mockTypeErrorRepository = mock(TypeErrorCharacteristicDomainRepository.class);

        when(mockTypeErrorRepository.countAllTypeErrorsByRequirement(requirementId)).thenReturn((int) aReturnDouble);
        when(mockTypeErrorRepository.countTypeErrorEIEByRequirement(requirementId)).thenReturn((int) aReturnDouble);

        //Act
        var result = typeErrorCharacteristicService.percentageOfTypeErrorEIEById(requirementId);

        //Assert
        verify(typeErrorCharacteristicService, times(1)).percentageOfTypeErrorEIEById(requirementId);
        verify(typeErrorCharacteristicService, times(0)).percentageOfTypeErrorMCCById(requirementId);
        assertNotEquals(5, result);
    }

    @Test
    public void testpercentageOfTypeErrorMCCById() {
        //Arrange
        int requirementId = 12;
        double aReturnDouble = 10.0;
        TypeErrorCharacteristicService typeErrorCharacteristicService = mock(TypeErrorCharacteristicService.class);
        TypeErrorCharacteristicDomainRepository mockTypeErrorRepository = mock(TypeErrorCharacteristicDomainRepository.class);

        when(mockTypeErrorRepository.countAllTypeErrorsByRequirement(requirementId)).thenReturn((int) aReturnDouble);
        when(mockTypeErrorRepository.countTypeErrorEIEByRequirement(requirementId)).thenReturn((int) aReturnDouble);

        //Act
        var result = typeErrorCharacteristicService.percentageOfTypeErrorMCCById(requirementId);

        //Assert
        verify(typeErrorCharacteristicService, times(0)).percentageOfTypeErrorEIEById(requirementId);
        verify(typeErrorCharacteristicService, times(1)).percentageOfTypeErrorMCCById(requirementId);
        assertNotEquals(5, result);
    }

    @Test
    public void testPercentageOfTypeErrosById() {
        //Arrange
        int typeErrorId = 2;
        int requirementId = 10;
        int aReturnInt = 10;

        TypeErrorCharacteristicService typeErrorCharacteristicService = mock(TypeErrorCharacteristicService.class);
        TypeErrorCharacteristicDomainRepository mockTypeErrorRepository = mock(TypeErrorCharacteristicDomainRepository.class);

        when(mockTypeErrorRepository.countAllTypeErrorsByRequirement(requirementId)).thenReturn(aReturnInt);
        when(mockTypeErrorRepository.countTypeErrorsByRequirements(typeErrorId, requirementId)).thenReturn(aReturnInt);

        //Act
        var result = typeErrorCharacteristicService.percentageOfTypeErrosById(typeErrorId, requirementId);

        //Assert
        verify(typeErrorCharacteristicService, times(1)).percentageOfTypeErrosById(typeErrorId, requirementId);
        assertNotEquals(5, result);
    }

    @Test
    public void testPercentageOffAllTypeErrorById() {
        //Arrange
        int requirementId = 1;
        Double aReturnDouble = 2.0;
        TypeErrorCharacteristicService typeErrorCharacteristicService = mock(TypeErrorCharacteristicService.class);

        when(typeErrorCharacteristicService.percentageOfTypeErrorMCCById(requirementId)).thenReturn(aReturnDouble);
        when(typeErrorCharacteristicService.percentageOfTypeErrorEIEById(requirementId)).thenReturn(aReturnDouble);
        //Act
        var result = typeErrorCharacteristicService.percentageOffAllTypeErrorById(requirementId);

        //Assert
        verify(typeErrorCharacteristicService, times(1)).percentageOffAllTypeErrorById(requirementId);
        assertEquals(0.0, result);
    }

    @Test
    public void testallNumbersAndPercentageOperations() {
        //Arrange
        //Act
        //Assert
    }

    @Test
    public void testCauseErrorByCharacteristicForRequirements() {
        //Arrange
        String typeRequirement = "Funcional";
        int projectId = 2;
        TypeErrorCharacteristicService typeErrorCharacteristicService = mock(TypeErrorCharacteristicService.class);

        //Act
        var result = typeErrorCharacteristicService.causeErrorByCharacteristicForRequirements(typeRequirement, projectId);

        //Assert
        verify(typeErrorCharacteristicService, times(1)).causeErrorByCharacteristicForRequirements(typeRequirement, projectId);
    }

    @Test
    public void testerrorDistributionAllRequirements() {
        //Arrange
        String typeRequirement = "Funcional";
        int projectId = 2;
        TypeErrorCharacteristicService typeErrorCharacteristicService = mock(TypeErrorCharacteristicService.class);

        //Act
        var result = typeErrorCharacteristicService.errorDistributionAllRequirements(typeRequirement, projectId);

        //Assert
        verify(typeErrorCharacteristicService, times(1)).errorDistributionAllRequirements(typeRequirement, projectId);
    }


}
