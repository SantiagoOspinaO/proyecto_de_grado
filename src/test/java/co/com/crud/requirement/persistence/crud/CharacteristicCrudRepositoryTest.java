package co.com.crud.requirement.persistence.crud;

import co.com.crud.requirement.domain.model.queryresult.ICharacteristicsByRequirementId;
import co.com.crud.requirement.domain.model.queryresult.IGradeCharacteristicByRequirementId;
import co.com.crud.requirement.persistence.CharacteristicRepository;
import co.com.crud.requirement.persistence.entity.CharacteristicEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
class CharacteristicCrudRepositoryTest {

    @Test
    void testFindTypeErrorCharacteristicById() {
        //Arrange
        CharacteristicRepository CharacteristicRepository = mock(CharacteristicRepository.class);
        ICharacteristicCrudRepository iCharacteristicCrudRepository = mock(ICharacteristicCrudRepository.class);
        when(iCharacteristicCrudRepository.findById(anyInt())).thenReturn(Optional.empty());
        Integer requirementId = 1;

        //Act
        Optional<CharacteristicEntity> result = iCharacteristicCrudRepository.findById(requirementId);

        //Assert
        verify(iCharacteristicCrudRepository, times(1)).findById(eq(requirementId));
        assertTrue(result.isEmpty());
    }

    @Test
    void testFindGradesByRequirementsWhenResultIsNull() {
        //Arrange
        int requisitoID = 1;
        CharacteristicRepository characteristicRepository = mock(CharacteristicRepository.class);
        ICharacteristicCrudRepository iCharacteristicCrudRepository = mock(ICharacteristicCrudRepository.class);
        List<IGradeCharacteristicByRequirementId> aReturnList = new ArrayList<IGradeCharacteristicByRequirementId>();
        when(iCharacteristicCrudRepository.findGradesByRequirementId(requisitoID)).thenReturn(aReturnList);

        //Act
        List<IGradeCharacteristicByRequirementId> result = iCharacteristicCrudRepository.findGradesByRequirementId(requisitoID);

        //Assert
        verify(iCharacteristicCrudRepository, times(1)).findGradesByRequirementId(requisitoID);
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testFindCharacteristicsByRequirementIDMustReturnEmpty() {
        //Arrange
        int requisitoID = 1;
        CharacteristicRepository characteristicRepository = mock(CharacteristicRepository.class);
        ICharacteristicCrudRepository iCharacteristicCrudRepository = mock(ICharacteristicCrudRepository.class);
        List<ICharacteristicsByRequirementId> aReturnList = new ArrayList<ICharacteristicsByRequirementId>();
        when(iCharacteristicCrudRepository.findCharacteristicsByRequirementId(requisitoID)).thenReturn(aReturnList);

        //Act
        List<ICharacteristicsByRequirementId> result = iCharacteristicCrudRepository.findCharacteristicsByRequirementId(requisitoID);

        //Assert
        verify(iCharacteristicCrudRepository, times(1)).findCharacteristicsByRequirementId(requisitoID);
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testUpdateGradeCharacteristicByRequirement() {
        //Arrange
        double notaIngresada = 8.8;
        int requisitoID = 1;
        int characteristicId = 1;
        ICharacteristicCrudRepository iCharacteristicCrudRepository = mock(ICharacteristicCrudRepository.class);

        //Act
        iCharacteristicCrudRepository.updateGradeCharacteristicByRequirement(notaIngresada, requisitoID, characteristicId);
        //Assert
        verify(iCharacteristicCrudRepository, times(1)).updateGradeCharacteristicByRequirement(notaIngresada, requisitoID, characteristicId);

    }

    @Test
    void testUpdateCauseErrorOfCharacteristic() {
        //Arrange
        int requisitoID = 1;
        int characteristicId = 1;
        Boolean dde = true, dii = false, var = true;
        ICharacteristicCrudRepository iCharacteristicCrudRepository = mock(ICharacteristicCrudRepository.class);

        //Act
        iCharacteristicCrudRepository.updateCauseErrorOfCharacteristic(dde, dii, var, requisitoID, characteristicId);

        //Assert
        verify(iCharacteristicCrudRepository, times(1)).updateCauseErrorOfCharacteristic(dde, dii, var, requisitoID, characteristicId);
    }

    @Test
    void testCountRequirementsByTypeAndNameCharacteristicMustReturnNull() {
        //Arrange
        String tipoRequisito = "Funcional";
        int projectId = 1;
        ICharacteristicCrudRepository iCharacteristicCrudRepository = mock(ICharacteristicCrudRepository.class);

        //Act
        var result = iCharacteristicCrudRepository.countRequirementsByTypeAndNameCharacteristic(tipoRequisito, projectId);

        //Assert
        verify(iCharacteristicCrudRepository, times(1)).countRequirementsByTypeAndNameCharacteristic(tipoRequisito, projectId);
        assertNull(result);
    }

    @Test
    void testCountCauseErrorByRequirementType() {
        //Arrange
        String tipoRequisito = "Funcional";
        int projectId = 1;
        ICharacteristicCrudRepository iCharacteristicCrudRepository = mock(ICharacteristicCrudRepository.class);

        //Act
        var result = iCharacteristicCrudRepository.countRequirementsByTypeAndCauseError(tipoRequisito, projectId);

        //Assert
        verify(iCharacteristicCrudRepository, times(1)).countRequirementsByTypeAndCauseError(tipoRequisito, projectId);
        assertNull(result);

    }

    @Test
    void testCountRequirementsByRequirementIdAndCauseErrorMustReturnNull() {
        //Arrange
        int requisitoId = 2;
        int projectId = 1;
        ICharacteristicCrudRepository iCharacteristicCrudRepository = mock(ICharacteristicCrudRepository.class);

        //Act
        var result = iCharacteristicCrudRepository.countRequirementsByRequirementIdAndCauseError(requisitoId, projectId);

        //Assert
        verify(iCharacteristicCrudRepository, times(1)).countRequirementsByRequirementIdAndCauseError(requisitoId, projectId);
        assertNull(result);
    }

    @Test
    void testCountCharacteristicsByCauseErrorDDEMustReturnNull() {
        //Arrange
        String typeRequirement = "funcional";
        int projectId = 1;
        ICharacteristicCrudRepository iCharacteristicCrudRepository = mock(ICharacteristicCrudRepository.class);

        //Act
        var result = iCharacteristicCrudRepository.countCharacteristicsByCauseErrorDDE(typeRequirement, projectId);

        //Assert
        verify(iCharacteristicCrudRepository, times(1)).countCharacteristicsByCauseErrorDDE(typeRequirement, projectId);
        assertNull(result);
    }

    @Test
    void testCountCharacteristicsByCauseErrorDII() {
        //Arrange
        String typeRequirement = "funcional";
        int projectId = 1;
        ICharacteristicCrudRepository iCharacteristicCrudRepository = mock(ICharacteristicCrudRepository.class);

        //Act
        var result = iCharacteristicCrudRepository.countCharacteristicsByCauseErrorDII(typeRequirement, projectId);

        //Assert
        verify(iCharacteristicCrudRepository, times(1)).countCharacteristicsByCauseErrorDII(typeRequirement, projectId);
        assertNull(result);
    }

    @Test
    void testCountCharacteristicsByCauseErrorVARMustReturnNull() {
        //Arrange
        String typeRequirement = "funcional";
        int projectId = 1;
        ICharacteristicCrudRepository iCharacteristicCrudRepository = mock(ICharacteristicCrudRepository.class);

        //Act
        var result = iCharacteristicCrudRepository.countCharacteristicsByCauseErrorVAR(typeRequirement, projectId);

        //Assert
        verify(iCharacteristicCrudRepository, times(1)).countCharacteristicsByCauseErrorVAR(typeRequirement, projectId);
        assertNull(result);
    }

}
