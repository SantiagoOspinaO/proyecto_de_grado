package co.com.crud.requirement.ControllerServiceTest;

import co.com.crud.requirement.domain.model.Characteristic;
import co.com.crud.requirement.domain.service.CharacteristicService;
import co.com.crud.requirement.persistence.crud.ICharacteristicCrudRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CharacteristicServiceMockTest {

    @MockBean
    ICharacteristicCrudRepository iCharacteristicCrudRepository;
    @Autowired
    CharacteristicService characteristicService;

    @Test
    public void testRequisitoMock() {
        when(iCharacteristicCrudRepository.findAll()).thenReturn(new ArrayList<>());
        assertThat(characteristicService.getAllCharacteristics().isEmpty());
        verify(iCharacteristicCrudRepository).findAll();
    }

    @Test
    public void testTodasCaracteristicas() {
        CharacteristicService characteristicService1 = Mockito.mock(CharacteristicService.class);
        Characteristic characteristic = new Characteristic();
        characteristic.setCharacteristicId(1);
        characteristic.setName("Correcto");
        characteristic.setOppositeName("Inequivoco");
        characteristic.setDescription("Se encuentra en el resultado o producto");
        characteristic.setOppositeDescription("No se encuentra en el resultado o producto");

        List<Characteristic> returnList = new ArrayList<Characteristic>();
        returnList.add(characteristic);
        when(characteristicService.getAllCharacteristics()).thenReturn(new ArrayList<Characteristic>());

        returnList = characteristicService1.getAllCharacteristics();

        verify(characteristicService1, times(1)).getAllCharacteristics();
    }
}
