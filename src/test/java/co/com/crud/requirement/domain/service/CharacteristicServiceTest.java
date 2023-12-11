package co.com.crud.requirement.domain.service;

import co.com.crud.requirement.domain.model.Characteristic;
import co.com.crud.requirement.domain.repository.CharacteristicDomainRepository;
import org.junit.Test;
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


}
