package co.com.crud.requirement.persistence.crud;

import co.com.crud.requirement.persistence.TypeErrorCharacteristicRepository;
import co.com.crud.requirement.persistence.entity.TypeErrorCharacteristicEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


@SpringBootTest
class TypeErrorCharacteristicCrudRepositoryTest {

    @Autowired
    private ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository;

    @Test
    void testFindTypeErrorCharacteristicById() {
        //Arrange
        TypeErrorCharacteristicRepository typeErrorCharacteristicRepository = mock(TypeErrorCharacteristicRepository.class);
        ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository = mock(ITypeErrorCharacteristicCrudRepository.class);
        when(iTypeErrorCharacteristicCrudRepository.findById(anyInt())).thenReturn(Optional.empty());
        Integer requirementId = 1;

        //Act
        Optional<TypeErrorCharacteristicEntity> result = iTypeErrorCharacteristicCrudRepository.findById(requirementId);

        //Assert
        verify(iTypeErrorCharacteristicCrudRepository, times(1)).findById(eq(requirementId));
        assertTrue(result.isEmpty());
    }

    @Test
    void testCountRequirementsByTypeAndErrorShoulReturnRequisito() {
        //Arrange
        int requisito = 1;
        TypeErrorCharacteristicRepository typeErrorCharacteristicRepository = mock(TypeErrorCharacteristicRepository.class);
        ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository = mock(ITypeErrorCharacteristicCrudRepository.class);
        when(iTypeErrorCharacteristicCrudRepository.countRequirementsByTypeAndCauseError(anyString(), anyString())).thenReturn(requisito);
        String tipoRequisito = "Funcional";
        String tipoError = "MCC";
        when(iTypeErrorCharacteristicCrudRepository.countRequirementsByTypeAndCauseError(eq(tipoRequisito), eq(tipoError))).thenReturn(requisito);

        //Act
        int result = iTypeErrorCharacteristicCrudRepository.countRequirementsByTypeAndCauseError(tipoRequisito, tipoError);

        //Assert
        verify(iTypeErrorCharacteristicCrudRepository, times(1)).countRequirementsByTypeAndCauseError(eq(tipoRequisito), eq(tipoError));
        assertEquals(1, result);
    }

    @Test
    void testCountRequirementsByTypeAndErrorReturnUnexpectedResult() {
        //Arrange
        int requisito = 1;
        TypeErrorCharacteristicRepository typeErrorCharacteristicRepository = mock(TypeErrorCharacteristicRepository.class);
        ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository = mock(ITypeErrorCharacteristicCrudRepository.class);
        when(iTypeErrorCharacteristicCrudRepository.countRequirementsByTypeAndCauseError(anyString(), anyString())).thenReturn(requisito);
        String tipoRequisito = "Funcional";
        String tipoError = "MCC";
        when(iTypeErrorCharacteristicCrudRepository.countRequirementsByTypeAndCauseError(eq(tipoRequisito), eq(tipoError))).thenReturn(requisito);

        //Act
        int result = iTypeErrorCharacteristicCrudRepository.countRequirementsByTypeAndCauseError(tipoRequisito, tipoError);

        //Assert
        verify(iTypeErrorCharacteristicCrudRepository, times(1)).countRequirementsByTypeAndCauseError(tipoRequisito, tipoError);
        assertNotEquals(10, result);
    }

    @Test
    void testCountRequirementsByCauseErrorAndRequirementId() {
        //Arrange
        int requisitoId = 10;
        int tipoErrorId = 2;
        String causaError = "DDE";
        ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository = mock(ITypeErrorCharacteristicCrudRepository.class);

        //Act
        iTypeErrorCharacteristicCrudRepository.countRequirementsByCauseErrorAndRequirementId(requisitoId, tipoErrorId, causaError);

        //Assert
        verify(iTypeErrorCharacteristicCrudRepository, times(1)).countRequirementsByCauseErrorAndRequirementId(requisitoId, tipoErrorId, causaError);
    }

    @Test
    void testCountRequirementsByCauseErrorDDEReturnExpectedResult() {
        //Arrange
        int requisito = 1;
        TypeErrorCharacteristicRepository typeErrorCharacteristicRepository = mock(TypeErrorCharacteristicRepository.class);
        ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository = mock(ITypeErrorCharacteristicCrudRepository.class);
        when(iTypeErrorCharacteristicCrudRepository.countRequirementsByCauseErrorDDE()).thenReturn(requisito);

        //Act
        int result = iTypeErrorCharacteristicCrudRepository.countRequirementsByCauseErrorDDE();

        //Assert
        verify(iTypeErrorCharacteristicCrudRepository, times(1)).countRequirementsByCauseErrorDDE();
        assertEquals(1, result);
    }

    @Test
    void testCountRequirementsByCauseErrorDDEReturnUnexpectedResult() {
        //Arrange
        int requisito = 1;
        TypeErrorCharacteristicRepository typeErrorCharacteristicRepository = mock(TypeErrorCharacteristicRepository.class);
        ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository = mock(ITypeErrorCharacteristicCrudRepository.class);
        when(iTypeErrorCharacteristicCrudRepository.countRequirementsByCauseErrorDDE()).thenReturn(requisito);

        //Act
        int result = iTypeErrorCharacteristicCrudRepository.countRequirementsByCauseErrorDDE();

        //Assert
        verify(iTypeErrorCharacteristicCrudRepository, times(1)).countRequirementsByCauseErrorDDE();
        assertNotEquals(10, result);
    }

    @Test
    void testCountRequirementsByCauseErrorDIIReturnExpectedResult() {
        //Arrange
        int requisito = 1;
        TypeErrorCharacteristicRepository typeErrorCharacteristicRepository = mock(TypeErrorCharacteristicRepository.class);
        ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository = mock(ITypeErrorCharacteristicCrudRepository.class);
        when(iTypeErrorCharacteristicCrudRepository.countRequirementsByCauseErrorDII()).thenReturn(requisito);

        //Act
        int result = iTypeErrorCharacteristicCrudRepository.countRequirementsByCauseErrorDII();

        //Assert
        verify(iTypeErrorCharacteristicCrudRepository, times(1)).countRequirementsByCauseErrorDII();
        assertEquals(1, result);
    }

    @Test
    void testCountRequirementsByCauseErrorDIIReturnUnexpectedResult() {
        //Arrange
        int requisito = 1;
        TypeErrorCharacteristicRepository typeErrorCharacteristicRepository = mock(TypeErrorCharacteristicRepository.class);
        ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository = mock(ITypeErrorCharacteristicCrudRepository.class);
        when(iTypeErrorCharacteristicCrudRepository.countRequirementsByCauseErrorDII()).thenReturn(requisito);

        //Act
        int result = iTypeErrorCharacteristicCrudRepository.countRequirementsByCauseErrorDII();

        //Assert
        verify(iTypeErrorCharacteristicCrudRepository, times(1)).countRequirementsByCauseErrorDII();
        assertNotEquals(10, result);
    }

    @Test
    void testCountRequirementsByCauseErrorVARReturnExpectedResult() {
        //Arrange
        int requisito = 1;
        TypeErrorCharacteristicRepository typeErrorCharacteristicRepository = mock(TypeErrorCharacteristicRepository.class);
        ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository = mock(ITypeErrorCharacteristicCrudRepository.class);
        when(iTypeErrorCharacteristicCrudRepository.countRequirementsByCauseErrorVAR()).thenReturn(requisito);


        //Act
        int result = iTypeErrorCharacteristicCrudRepository.countRequirementsByCauseErrorVAR();

        //Assert
        verify(iTypeErrorCharacteristicCrudRepository, times(1)).countRequirementsByCauseErrorVAR();
        assertEquals(1, result);
    }

    @Test
    void testcountRequirementsByErrorVARReturnUnexpectedResult() {
        //Arrange
        int requisito = 1;
        TypeErrorCharacteristicRepository typeErrorCharacteristicRepository = mock(TypeErrorCharacteristicRepository.class);
        ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository = mock(ITypeErrorCharacteristicCrudRepository.class);
        when(iTypeErrorCharacteristicCrudRepository.countRequirementsByCauseErrorVAR()).thenReturn(requisito);

        //Act
        int result = iTypeErrorCharacteristicCrudRepository.countRequirementsByCauseErrorVAR();

        //Assert
        verify(iTypeErrorCharacteristicCrudRepository, times(1)).countRequirementsByCauseErrorVAR();
        assertNotEquals(10, result);
    }

    @Test
    void testCountTypeErrorEIEByRequirement() {
        //Arrange
        int requisito = 1;
        ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository = mock(ITypeErrorCharacteristicCrudRepository.class);
        when(iTypeErrorCharacteristicCrudRepository.countRequirementsByCauseErrorVAR()).thenReturn(requisito);

        //Act
        int result = iTypeErrorCharacteristicCrudRepository.countTypeErrorEIEByRequirement(requisito);


        //Assert
        verify(iTypeErrorCharacteristicCrudRepository, times(1)).countTypeErrorEIEByRequirement(requisito);
        assertNotEquals(10, result);

    }

    @Test
    void testCountTypeErrorMCCByRequirementReturnExpectedResult() {
        //Arrange
        int requisito = 1;
        TypeErrorCharacteristicRepository typeErrorCharacteristicRepository = mock(TypeErrorCharacteristicRepository.class);
        ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository = mock(ITypeErrorCharacteristicCrudRepository.class);
        when(iTypeErrorCharacteristicCrudRepository.countRequirementsByCauseErrorVAR()).thenReturn(requisito);

        //Act
        int result = iTypeErrorCharacteristicCrudRepository.countTypeErrorMCCByRequirement(requisito);

        //Assert
        verify(iTypeErrorCharacteristicCrudRepository, times(1)).countTypeErrorMCCByRequirement(requisito);
        assertNotEquals(1, result);
    }

    @Test
    void testCountTypeErrorMCCByRequirementReturnUnexpectedResult() {
        //Arrange
        int requisito = 1;
        TypeErrorCharacteristicRepository typeErrorCharacteristicRepository = mock(TypeErrorCharacteristicRepository.class);
        ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository = mock(ITypeErrorCharacteristicCrudRepository.class);
        when(iTypeErrorCharacteristicCrudRepository.countRequirementsByCauseErrorVAR()).thenReturn(requisito);

        //Act
        int result = iTypeErrorCharacteristicCrudRepository.countTypeErrorMCCByRequirement(requisito);

        //Assert
        verify(iTypeErrorCharacteristicCrudRepository, times(1)).countTypeErrorMCCByRequirement(requisito);
        assertNotEquals(10, result);
    }

    @Test
    void testcountTypeErrorEIEByRequirementReturnExpectedResult() {
        //Arrange
        int requisito = 1;
        TypeErrorCharacteristicRepository typeErrorCharacteristicRepository = mock(TypeErrorCharacteristicRepository.class);
        ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository = mock(ITypeErrorCharacteristicCrudRepository.class);
        when(iTypeErrorCharacteristicCrudRepository.countRequirementsByCauseErrorVAR()).thenReturn(requisito);

        //Act
        int result = iTypeErrorCharacteristicCrudRepository.countTypeErrorEIEByRequirement(requisito);

        //Assert
        verify(iTypeErrorCharacteristicCrudRepository, times(1)).countTypeErrorEIEByRequirement(requisito);
        assertNotEquals(1, result);
    }

    @Test
    void testcountTypeErrorEIEByRequirementReturnUnexpectedResult() {
        //Arrange
        int requisito = 1;
        TypeErrorCharacteristicRepository typeErrorCharacteristicRepository = mock(TypeErrorCharacteristicRepository.class);
        ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository = mock(ITypeErrorCharacteristicCrudRepository.class);
        when(iTypeErrorCharacteristicCrudRepository.countRequirementsByCauseErrorVAR()).thenReturn(requisito);

        //Act
        int result = iTypeErrorCharacteristicCrudRepository.countTypeErrorEIEByRequirement(requisito);

        //Assert
        verify(iTypeErrorCharacteristicCrudRepository, times(1)).countTypeErrorEIEByRequirement(requisito);
        assertNotEquals(10, result);
    }

    @Test
    void testCountTypeErrorsByRequirementReturnExpectedResult() {
        //Arrange
        int requisito = 1;
        TypeErrorCharacteristicRepository typeErrorCharacteristicRepository = mock(TypeErrorCharacteristicRepository.class);
        ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository = mock(ITypeErrorCharacteristicCrudRepository.class);
        when(iTypeErrorCharacteristicCrudRepository.countRequirementsByCauseErrorVAR()).thenReturn(requisito);

        //Act
        int result = iTypeErrorCharacteristicCrudRepository.countAllTypeErrorsByRequirement(requisito);

        //Assert
        verify(iTypeErrorCharacteristicCrudRepository, times(1)).countAllTypeErrorsByRequirement(requisito);
        assertNotEquals(1, result);
    }

    @Test
    void testCountTypeErrorsByRequirementReturnUnexpectedResult() {
        //Arrange
        int requisito = 1;
        TypeErrorCharacteristicRepository typeErrorCharacteristicRepository = mock(TypeErrorCharacteristicRepository.class);
        ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository = mock(ITypeErrorCharacteristicCrudRepository.class);
        when(iTypeErrorCharacteristicCrudRepository.countRequirementsByCauseErrorVAR()).thenReturn(requisito);

        //Act
        int result = iTypeErrorCharacteristicCrudRepository.countAllTypeErrorsByRequirement(requisito);

        //Assert
        verify(iTypeErrorCharacteristicCrudRepository, times(1)).countAllTypeErrorsByRequirement(requisito);
        assertNotEquals(10, result);
    }

    @Test
    void testCountTypeErrorEIEAndCauseErrorDDEByRequirement() {
        //Arrange
        int requisito = 1;
        TypeErrorCharacteristicRepository typeErrorCharacteristicRepository = mock(TypeErrorCharacteristicRepository.class);
        ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository = mock(ITypeErrorCharacteristicCrudRepository.class);
        when(iTypeErrorCharacteristicCrudRepository.countTypeErrorEIEAndCauseErrorDDEByRequirement(anyInt())).thenReturn(requisito);

        //Act
        int result = iTypeErrorCharacteristicCrudRepository.countTypeErrorEIEAndCauseErrorDDEByRequirement(requisito);

        //Assert
        verify(iTypeErrorCharacteristicCrudRepository, times(1)).countTypeErrorEIEAndCauseErrorDDEByRequirement(requisito);
        assertNotEquals(5, result);
    }

    @Test
    void testCountTypeErrorEIEAndCauseErrorDIIByRequirement() {
        //Arrange
        int requisito = 1;
        TypeErrorCharacteristicRepository typeErrorCharacteristicRepository = mock(TypeErrorCharacteristicRepository.class);
        ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository = mock(ITypeErrorCharacteristicCrudRepository.class);
        when(iTypeErrorCharacteristicCrudRepository.countTypeErrorEIEAndCauseErrorDIIByRequirement(anyInt())).thenReturn(requisito);

        //Act
        int result = iTypeErrorCharacteristicCrudRepository.countTypeErrorEIEAndCauseErrorDIIByRequirement(requisito);

        //Assert
        verify(iTypeErrorCharacteristicCrudRepository, times(1)).countTypeErrorEIEAndCauseErrorDIIByRequirement(requisito);
        assertNotEquals(8, result);
    }

    @Test
    void testCountTypeErrorEIEAndCauseErrorVARByRequirement() {
        //Arrange
        int requisito = 1;
        TypeErrorCharacteristicRepository typeErrorCharacteristicRepository = mock(TypeErrorCharacteristicRepository.class);
        ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository = mock(ITypeErrorCharacteristicCrudRepository.class);
        when(iTypeErrorCharacteristicCrudRepository.countTypeErrorEIEAndCauseErrorVARByRequirement(anyInt())).thenReturn(requisito);

        //Act
        int result = iTypeErrorCharacteristicCrudRepository.countTypeErrorEIEAndCauseErrorVARByRequirement(requisito);

        //Assert
        verify(iTypeErrorCharacteristicCrudRepository, times(1)).countTypeErrorEIEAndCauseErrorVARByRequirement(requisito);
        assertNotEquals(8, result);
    }

    @Test
    void testCountTypeErrorMCCByRequirement() {
        //Arrange
        int requisito = 1;
        TypeErrorCharacteristicRepository typeErrorCharacteristicRepository = mock(TypeErrorCharacteristicRepository.class);
        ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository = mock(ITypeErrorCharacteristicCrudRepository.class);
        when(iTypeErrorCharacteristicCrudRepository.countTypeErrorMCCByRequirement(anyInt())).thenReturn(requisito);

        //Act
        int result = iTypeErrorCharacteristicCrudRepository.countTypeErrorMCCByRequirement(requisito);

        //Assert
        verify(iTypeErrorCharacteristicCrudRepository, times(1)).countTypeErrorMCCByRequirement(requisito);
        assertNotEquals(8, result);

    }

    @Test
    void testCountTypeErrorMCCAndCauseErrorDDEByRequirement() {
        //Arrange
        int requisito = 1;
        TypeErrorCharacteristicRepository typeErrorCharacteristicRepository = mock(TypeErrorCharacteristicRepository.class);
        ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository = mock(ITypeErrorCharacteristicCrudRepository.class);
        when(iTypeErrorCharacteristicCrudRepository.countTypeErrorMCCAndCauseErrorDDEByRequirement(anyInt())).thenReturn(requisito);

        //Act
        int result = iTypeErrorCharacteristicCrudRepository.countTypeErrorMCCAndCauseErrorDDEByRequirement(requisito);

        //Assert
        verify(iTypeErrorCharacteristicCrudRepository, times(1)).countTypeErrorMCCAndCauseErrorDDEByRequirement(requisito);
        assertNotEquals(8, result);

    }

    @Test
    void testCountTypeErrorMCCAndCauseErrorDIIByRequirement() {
        //Arrange
        int requisito = 1;
        TypeErrorCharacteristicRepository typeErrorCharacteristicRepository = mock(TypeErrorCharacteristicRepository.class);
        ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository = mock(ITypeErrorCharacteristicCrudRepository.class);
        when(iTypeErrorCharacteristicCrudRepository.countTypeErrorMCCAndCauseErrorDIIByRequirement(anyInt())).thenReturn(requisito);

        //Act
        int result = iTypeErrorCharacteristicCrudRepository.countTypeErrorMCCAndCauseErrorDIIByRequirement(requisito);

        //Assert
        verify(iTypeErrorCharacteristicCrudRepository, times(1)).countTypeErrorMCCAndCauseErrorDIIByRequirement(requisito);
        assertNotEquals(8, result);

    }

    @Test
    void testcountTypeErrorMCCAndCauseErrorVARByRequirement() {
        //Arrange
        int requisito = 1;
        TypeErrorCharacteristicRepository typeErrorCharacteristicRepository = mock(TypeErrorCharacteristicRepository.class);
        ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository = mock(ITypeErrorCharacteristicCrudRepository.class);
        when(iTypeErrorCharacteristicCrudRepository.countTypeErrorMCCAndCauseErrorVARByRequirement(anyInt())).thenReturn(requisito);

        //Act
        int result = iTypeErrorCharacteristicCrudRepository.countTypeErrorMCCAndCauseErrorVARByRequirement(requisito);

        //Assert
        verify(iTypeErrorCharacteristicCrudRepository, times(1)).countTypeErrorMCCAndCauseErrorVARByRequirement(requisito);
        assertNotEquals(8, result);
    }

    @Test
    void testCountTypeErrorsByRequirements() {
        //Arrange
        int requisitoId = 10;
        int tipoErrorId = 2;
        int returnRequisito = 1;
        TypeErrorCharacteristicRepository typeErrorCharacteristicRepository = mock(TypeErrorCharacteristicRepository.class);
        ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository = mock(ITypeErrorCharacteristicCrudRepository.class);
        when(iTypeErrorCharacteristicCrudRepository.countTypeErrorsByRequirements(anyInt(), anyInt())).thenReturn(returnRequisito);

        //Act
        int result = iTypeErrorCharacteristicCrudRepository.countTypeErrorsByRequirements(tipoErrorId, requisitoId);

        //Assert
        verify(iTypeErrorCharacteristicCrudRepository, times(1)).countTypeErrorsByRequirements(tipoErrorId, requisitoId);
        assertNotEquals(8, result);
    }

    @Test
    void testcountAllTypeErrorsByRequirement() {
        //Arrange
        int requisito = 1;
        TypeErrorCharacteristicRepository typeErrorCharacteristicRepository = mock(TypeErrorCharacteristicRepository.class);
        ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository = mock(ITypeErrorCharacteristicCrudRepository.class);
        when(iTypeErrorCharacteristicCrudRepository.countAllTypeErrorsByRequirement(anyInt())).thenReturn(requisito);

        //Act
        int result = iTypeErrorCharacteristicCrudRepository.countAllTypeErrorsByRequirement(requisito);

        //Assert
        verify(iTypeErrorCharacteristicCrudRepository, times(1)).countAllTypeErrorsByRequirement(requisito);
        assertNotEquals(8, result);
    }

    @Test
    void testCountAllCauseErrorsByRequirement() {
        //Arrange
        int requisito = 1;
        TypeErrorCharacteristicRepository typeErrorCharacteristicRepository = mock(TypeErrorCharacteristicRepository.class);
        ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository = mock(ITypeErrorCharacteristicCrudRepository.class);
        when(iTypeErrorCharacteristicCrudRepository.countAllCauseErrorsByRequirement(anyInt())).thenReturn(requisito);

        //Act
        int result = iTypeErrorCharacteristicCrudRepository.countAllCauseErrorsByRequirement(requisito);

        //Assert
        verify(iTypeErrorCharacteristicCrudRepository, times(1)).countAllCauseErrorsByRequirement(requisito);
        assertNotEquals(8, result);
    }

    @Test
    void testCauseErrorByCharacteristicForRequirements() {
        //Arrange
        int projectId = 10;
        String tipoRequisito = "Funcional";
        TypeErrorCharacteristicRepository typeErrorCharacteristicRepository = mock(TypeErrorCharacteristicRepository.class);
        ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository = mock(ITypeErrorCharacteristicCrudRepository.class);

        //Act
        iTypeErrorCharacteristicCrudRepository.causeErrorByCharacteristicForRequirements(tipoRequisito, projectId);

        //Assert
        verify(iTypeErrorCharacteristicCrudRepository, times(1)).causeErrorByCharacteristicForRequirements(tipoRequisito, projectId);

    }

    @Test
    void testErrorDistributionAllRequirements() {
        //Arrange
        int projectId = 10;
        String tipoRequisito = "Funcional";
        TypeErrorCharacteristicRepository typeErrorCharacteristicRepository = mock(TypeErrorCharacteristicRepository.class);
        ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository = mock(ITypeErrorCharacteristicCrudRepository.class);

        //Act
        iTypeErrorCharacteristicCrudRepository.errorDistributionAllRequirements(tipoRequisito, projectId);

        //Assert
        verify(iTypeErrorCharacteristicCrudRepository, times(1)).errorDistributionAllRequirements(tipoRequisito, projectId);

    }

}
