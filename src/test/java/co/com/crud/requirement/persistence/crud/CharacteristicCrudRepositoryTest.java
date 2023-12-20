package co.com.crud.requirement.persistence.crud;

import co.com.crud.requirement.domain.model.queryresult.ICharacteristicsByRequirementId;
import co.com.crud.requirement.domain.model.queryresult.IGradeCharacteristicByRequirementId;
import co.com.crud.requirement.persistence.CharacteristicRepository;
import co.com.crud.requirement.persistence.entity.CharacteristicEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CharacteristicCrudRepositoryTest {

    @Test
    public void testFindTypeErrorCharacteristicById() {
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
    public void testFindGradesByRequirementsWhenResultIsNull(){
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
        assertEquals(Collections.emptyList(),result);
    }

    @Test
    public void testFindCharacteristicsByRequirementIDMustReturnEmpty(){
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
        assertEquals(Collections.emptyList(),result);
    }

    @Test
    public void testupdateCharacteristicByRequirementId(){
        //Arrange
        int requisitoID = 1;
        int characteristicId = 1;
        String nombre = "Correcto";
        String descripcion = "Requisito para revisar niveles de adecuacion";
        String nombreOpuesto = "Incorrecto";
        String descripcionOpuesta = "PRueba";
        Double notaCaracteristica = 9.0;
        Boolean dde = true , dii = false, var = true;
        ICharacteristicCrudRepository iCharacteristicCrudRepository = mock(ICharacteristicCrudRepository.class);

        //Act
        iCharacteristicCrudRepository.updateCharacteristicByRequirementId(requisitoID,characteristicId,nombre,descripcion,nombreOpuesto,descripcionOpuesta,notaCaracteristica, dde,dii,var);

        //Assert
        verify(iCharacteristicCrudRepository, times(1)).updateCharacteristicByRequirementId(requisitoID,characteristicId,nombre,descripcion,nombreOpuesto,descripcionOpuesta,notaCaracteristica, dde,dii,var);

    }

    @Test
    public void testUpdateTypeErrorOfCharacteristic(){
        //Arrange
        int requisitoID = 1;
        int characteristicId = 1;
        Boolean dde = true , dii = false, var = true;
        ICharacteristicCrudRepository iCharacteristicCrudRepository = mock(ICharacteristicCrudRepository.class);

        //Act
        iCharacteristicCrudRepository.updateCauseErrorOfCharacteristic(dde,dii,var, requisitoID,characteristicId);

        //Assert
        verify(iCharacteristicCrudRepository, times(1)).updateCauseErrorOfCharacteristic(dde,dii,var,requisitoID,characteristicId);
    }
}
