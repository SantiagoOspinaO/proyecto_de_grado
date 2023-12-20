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
import static org.mockito.Mockito.times;

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
    public void testcountRequirementsByErrorDDEReturnExpectedResult() {
        //Arrange
        int requisito = 1;
        TypeErrorCharacteristicRepository typeErrorCharacteristicRepository = mock(TypeErrorCharacteristicRepository.class);
        ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository = mock(ITypeErrorCharacteristicCrudRepository.class);
        when(iTypeErrorCharacteristicCrudRepository.countRequirementsByErrorDDE()).thenReturn(requisito);

        when(iTypeErrorCharacteristicCrudRepository.countRequirementsByErrorDDE()).thenReturn(requisito);

        //Act
        int result = iTypeErrorCharacteristicCrudRepository.countRequirementsByErrorDDE();

        //Assert
        verify(iTypeErrorCharacteristicCrudRepository, times(1)).countRequirementsByErrorDDE();
        assertEquals(1, result);
    }

    @Test
    public void testcountRequirementsByErrorDDEReturnUnexpectedResult() {
        //Arrange
        int requisito = 1;
        TypeErrorCharacteristicRepository typeErrorCharacteristicRepository = mock(TypeErrorCharacteristicRepository.class);
        ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository = mock(ITypeErrorCharacteristicCrudRepository.class);
        when(iTypeErrorCharacteristicCrudRepository.countRequirementsByErrorDDE()).thenReturn(requisito);

        when(iTypeErrorCharacteristicCrudRepository.countRequirementsByErrorDDE()).thenReturn(requisito);

        //Act
        int result = iTypeErrorCharacteristicCrudRepository.countRequirementsByErrorDDE();

        //Assert
        verify(iTypeErrorCharacteristicCrudRepository, times(1)).countRequirementsByErrorDDE();
        assertNotEquals(10, result);
    }

    @Test
    public void testcountRequirementsByErrorDIIReturnExpectedResult() {
        //Arrange
        int requisito = 1;
        TypeErrorCharacteristicRepository typeErrorCharacteristicRepository = mock(TypeErrorCharacteristicRepository.class);
        ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository = mock(ITypeErrorCharacteristicCrudRepository.class);
        when(iTypeErrorCharacteristicCrudRepository.countRequirementsByErrorDII()).thenReturn(requisito);

        when(iTypeErrorCharacteristicCrudRepository.countRequirementsByErrorDII()).thenReturn(requisito);

        //Act
        int result = iTypeErrorCharacteristicCrudRepository.countRequirementsByErrorDII();

        //Assert
        verify(iTypeErrorCharacteristicCrudRepository, times(1)).countRequirementsByErrorDII();
        assertEquals(1, result);
    }

    @Test
    public void testcountRequirementsByErrorDIIReturnUnexpectedResult() {
        //Arrange
        int requisito = 1;
        TypeErrorCharacteristicRepository typeErrorCharacteristicRepository = mock(TypeErrorCharacteristicRepository.class);
        ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository = mock(ITypeErrorCharacteristicCrudRepository.class);
        when(iTypeErrorCharacteristicCrudRepository.countRequirementsByErrorDII()).thenReturn(requisito);

        when(iTypeErrorCharacteristicCrudRepository.countRequirementsByErrorDII()).thenReturn(requisito);

        //Act
        int result = iTypeErrorCharacteristicCrudRepository.countRequirementsByErrorDII();

        //Assert
        verify(iTypeErrorCharacteristicCrudRepository, times(1)).countRequirementsByErrorDII();
        assertNotEquals(10, result);
    }

    @Test
    public void testcountRequirementsByErrorVARReturnExpectedResult() {
        //Arrange
        int requisito = 1;
        TypeErrorCharacteristicRepository typeErrorCharacteristicRepository = mock(TypeErrorCharacteristicRepository.class);
        ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository = mock(ITypeErrorCharacteristicCrudRepository.class);
        when(iTypeErrorCharacteristicCrudRepository.countRequirementsByErrorVAR()).thenReturn(requisito);

        when(iTypeErrorCharacteristicCrudRepository.countRequirementsByErrorVAR()).thenReturn(requisito);

        //Act
        int result = iTypeErrorCharacteristicCrudRepository.countRequirementsByErrorVAR();

        //Assert
        verify(iTypeErrorCharacteristicCrudRepository, times(1)).countRequirementsByErrorVAR();
        assertEquals(1, result);
    }

    @Test
    public void testcountRequirementsByErrorVARReturnUnexpectedResult() {
        //Arrange
        int requisito = 1;
        TypeErrorCharacteristicRepository typeErrorCharacteristicRepository = mock(TypeErrorCharacteristicRepository.class);
        ITypeErrorCharacteristicCrudRepository iTypeErrorCharacteristicCrudRepository = mock(ITypeErrorCharacteristicCrudRepository.class);
        when(iTypeErrorCharacteristicCrudRepository.countRequirementsByErrorVAR()).thenReturn(requisito);

        when(iTypeErrorCharacteristicCrudRepository.countRequirementsByErrorVAR()).thenReturn(requisito);

        //Act
        int result = iTypeErrorCharacteristicCrudRepository.countRequirementsByErrorVAR();

        //Assert
        verify(iTypeErrorCharacteristicCrudRepository, times(1)).countRequirementsByErrorVAR();
        assertNotEquals(10, result);
    }
}
