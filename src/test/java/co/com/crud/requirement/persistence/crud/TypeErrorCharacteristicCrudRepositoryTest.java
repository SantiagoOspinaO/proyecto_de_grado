package co.com.crud.requirement.persistence.crud;

import co.com.crud.requirement.persistence.TypeErrorCharacteristicRepository;
import co.com.crud.requirement.persistence.entity.TypeErrorCharacteristicEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TypeErrorCharacteristicCrudRepositoryTest {

    @Autowired
    private ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository;

    @Test
    public void testFindTypeErrorCharacteristicById() {
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
    public void testCountRequirementsByTypeAndErrorShoulReturnRequisito() {
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
    public void testCountRequirementsByTypeAndErrorReturnUnexpectedResult() {
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
        assertNotEquals(10, result);
    }

    @Test
    public void testCountRequirementsByCauseErrorDDEReturnExpectedResult() {
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
    public void testCountRequirementsByCauseErrorDDEReturnUnexpectedResult() {
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
    public void testCountRequirementsByCauseErrorDIIReturnExpectedResult() {
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
    public void testCountRequirementsByCauseErrorDIIReturnUnexpectedResult() {
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
    public void testCountRequirementsByCauseErrorVARReturnExpectedResult() {
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
    public void testcountRequirementsByErrorVARReturnUnexpectedResult() {
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
    public void testCountTypeErrorMCCByRequirementReturnExpectedResult() {
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
    public void testCountTypeErrorMCCByRequirementReturnUnexpectedResult() {
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
    public void testcountTypeErrorEIEByRequirementReturnExpectedResult() {
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
    public void testcountTypeErrorEIEByRequirementReturnUnexpectedResult() {
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
    public void testCountTypeErrorsByRequirementReturnExpectedResult() {
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
    public void testCountTypeErrorsByRequirementReturnUnexpectedResult() {
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
}
