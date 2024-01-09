package co.com.crud.requirement.domain.service;

import co.com.crud.requirement.domain.model.Characteristic;
import co.com.crud.requirement.domain.model.Requirement;
import co.com.crud.requirement.domain.model.queryresult.*;
import co.com.crud.requirement.domain.repository.CharacteristicDomainRepository;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CharacteristicServiceTest {

    private CharacteristicDomainRepository characteristicDomainRepository;
    @MockBean
    CharacteristicDomainRepository CharacteristicDomainRepository;
    @Test
    public void testGetAllCharacteristicsWhenResultIsNotNull() {
        //Arrange
        CharacteristicDomainRepository mockCharacteristicRepository = mock(CharacteristicDomainRepository.class);
        Characteristic characteristic = new Characteristic();
        characteristic.setCharacteristicId( 1 );
        characteristic.setName( "Correcto");
        characteristic.setOppositeName( "Inequivoco");
        characteristic.setDescription("Se encuentra en el resultado o producto");
        characteristic.setOppositeDescription("No se encuentra en el resultado o producto");

        ArrayList<Characteristic> mockCharacteristics = new ArrayList<Characteristic>();
        mockCharacteristics.add(characteristic);
        when(mockCharacteristicRepository.getAllCharacteristics()).thenReturn(mockCharacteristics);
        CharacteristicService characteristicService = new CharacteristicService(mockCharacteristicRepository);

        //Act
        List<Characteristic> result = characteristicService.getAllCharacteristics();

        //ASSERT
        verify(mockCharacteristicRepository, times(1)).getAllCharacteristics();
        assertNotNull(result);
    }

    @Test
    public void testGetCharacteristicByRequirementId(){
        //Arrange
        CharacteristicDomainRepository mockCharacteristicRepository = mock(CharacteristicDomainRepository.class);
        Requirement requirement = new Requirement();
        requirement.setRequirementId(1);
        requirement.setProjectId(1);
        requirement.setName("Requisito ensayo");
        requirement.setDescription("Requisito ensayo prueba");
        requirement.setTypeRequirement("Funcional");

        List<ICharacteristicsByRequirementId> mockICharacteristics = new ArrayList<ICharacteristicsByRequirementId>();
        when(mockCharacteristicRepository.getCharacteristicsByRequirementId(requirement.getRequirementId())).thenReturn(mockICharacteristics);
        CharacteristicService characteristicService = new CharacteristicService(mockCharacteristicRepository);
        //Act
        List<ICharacteristicsByRequirementId> result = characteristicService.getCharacteristicByRequirement(requirement.getRequirementId());
        //Assert
        Mockito.verify(mockCharacteristicRepository, Mockito.times(1)).getCharacteristicsByRequirementId(requirement.getRequirementId());
        assertNotNull(result);
    }

    @Test
    public void testCountRequirementsByTypeAndNameCharacteristic(){
        //Arrange
        CharacteristicDomainRepository mockCharacteristicRepository = mock(CharacteristicDomainRepository.class);
        IRequirementsByTypeAndNameCharacteristic mockIRequirementsByTypeAndNameCharacteristic = mock(IRequirementsByTypeAndNameCharacteristic.class);
        Requirement requirement = new Requirement();
        requirement.setRequirementId(1);
        requirement.setProjectId(1);
        requirement.setName("Requisito ensayo");
        requirement.setDescription("Requisito ensayo prueba");
        requirement.setTypeRequirement("Funcional");

        when(mockCharacteristicRepository.countRequirementsByTypeAndNameCharacteristic(any(), any())).thenReturn(mockIRequirementsByTypeAndNameCharacteristic);

        //Act
        IRequirementsByTypeAndNameCharacteristic result = mockCharacteristicRepository.countRequirementsByTypeAndNameCharacteristic(requirement.getTypeRequirement(), requirement.getProjectId());
        //Assert
        Mockito.verify(mockCharacteristicRepository, Mockito.times(1)).countRequirementsByTypeAndNameCharacteristic(requirement.getTypeRequirement(), requirement.getProjectId());
        assertNotNull(result);
    }

    @Test
    public void testCountRequirementsByRequirementIdAndCauseError(){
        //Arrange
        CharacteristicDomainRepository mockCharacteristicRepository = mock(CharacteristicDomainRepository.class);
        IRequirementsByRequirementIdAndCauseError mockIRequirementsByRequirementIdAndCauseError = mock(IRequirementsByRequirementIdAndCauseError.class);
        Requirement requirement = new Requirement();
        requirement.setRequirementId(1);
        requirement.setProjectId(1);
        requirement.setName("Requisito ensayo");
        requirement.setDescription("Requisito ensayo prueba");
        requirement.setTypeRequirement("Funcional");

        when(mockCharacteristicRepository.countRequirementsByRequirementIdAndCauseError(any(), any())).thenReturn(mockIRequirementsByRequirementIdAndCauseError);

        //Act
        IRequirementsByRequirementIdAndCauseError result = mockCharacteristicRepository.countRequirementsByRequirementIdAndCauseError(requirement.getRequirementId(), requirement.getProjectId());
        //Assert
        Mockito.verify(mockCharacteristicRepository, Mockito.times(1)).countRequirementsByRequirementIdAndCauseError(requirement.getRequirementId(), requirement.getProjectId());
        assertNotNull(result);
    }
    @Test
    public void testCountCauseErrorByRequirementType(){
        //Arrange
        CharacteristicDomainRepository mockCharacteristicRepository = mock(CharacteristicDomainRepository.class);
        IRequirementsByFilterCauseError mockIRequirementsByFilterCauseError = mock(IRequirementsByFilterCauseError.class);
        Requirement requirement = new Requirement();
        requirement.setRequirementId(1);
        requirement.setProjectId(1);
        requirement.setName("Requisito ensayo");
        requirement.setDescription("Requisito ensayo prueba");
        requirement.setTypeRequirement("Funcional");

        when(mockCharacteristicRepository.countCauseErrorByRequirementType(any(), any())).thenReturn(mockIRequirementsByFilterCauseError);

        //Act
        IRequirementsByFilterCauseError result = mockCharacteristicRepository.countCauseErrorByRequirementType(requirement.getTypeRequirement(), requirement.getProjectId());

        //Assert
        Mockito.verify(mockCharacteristicRepository, Mockito.times(1)).countCauseErrorByRequirementType(requirement.getTypeRequirement(), requirement.getProjectId());
        assertNotNull(result);
    }

    @Test
    public void testCountRequirementsByTypeAndCauseError(){
        //Arrange
        CharacteristicDomainRepository mockCharacteristicRepository = mock(CharacteristicDomainRepository.class);
        IRequirementsByTypeAndCauseError mockIRequirementsByTypeAndCauseError = mock(IRequirementsByTypeAndCauseError.class);
        Requirement requirement = new Requirement();
        requirement.setRequirementId(1);
        requirement.setProjectId(1);
        requirement.setName("Requisito ensayo");
        requirement.setDescription("Requisito ensayo prueba");
        requirement.setTypeRequirement("Funcional");

        when(mockCharacteristicRepository.countRequirementsByTypeAndCauseError(any(), any())).thenReturn(mockIRequirementsByTypeAndCauseError);

        //Act
        IRequirementsByTypeAndCauseError result = mockCharacteristicRepository.countRequirementsByTypeAndCauseError(requirement.getTypeRequirement(), requirement.getProjectId());

        //Assert
        Mockito.verify(mockCharacteristicRepository, Mockito.times(1)).countRequirementsByTypeAndCauseError(requirement.getTypeRequirement(), requirement.getProjectId());
        assertNotNull(result);
    }

    @Test
    public void testLevelWeightScoreForNineCharacters(){
        //Arrange
        CharacteristicDomainRepository mockCharacteristicRepository = mock(CharacteristicDomainRepository.class);
        CharacteristicService mockCharacteristicService = mock(CharacteristicService.class);
        Requirement requirement = new Requirement();
        requirement.setRequirementId(1);
        requirement.setProjectId(1);
        requirement.setName("Requisito ensayo");
        requirement.setDescription("Requisito ensayo prueba");
        requirement.setTypeRequirement("Funcional");
        Double maxScore= 81.0;

        when(mockCharacteristicService.maximumAccumulatedScore(requirement.getRequirementId())).thenReturn(maxScore);

        //Act
        double result = mockCharacteristicService.levelWeightScoreForNineCharacters(requirement.getRequirementId());

        //Assert
        Mockito.verify(mockCharacteristicService, Mockito.times(1)).levelWeightScoreForNineCharacters(requirement.getRequirementId());
        assertNotNull(result);
    }



}
