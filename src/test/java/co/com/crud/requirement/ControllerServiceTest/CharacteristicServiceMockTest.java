package co.com.crud.requirement.ControllerServiceTest;

import co.com.crud.requirement.domain.model.Characteristic;
import co.com.crud.requirement.domain.service.CharacteristicService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
public class CharacteristicServiceMockTest {

    @Autowired
    CharacteristicService characteristicServices;

    @Test
    public void testTodasCaracteristicas() {
        CharacteristicService characteristicService = Mockito.mock(CharacteristicService.class);
        Characteristic characteristic = new Characteristic();
        characteristic.setCharacteristicId(1);
        characteristic.setName("Correcto");
        characteristic.setOppositeName("Inequivoco");
        characteristic.setDescription("Se encuentra en el resultado o producto");
        characteristic.setOppositeDescription("No se encuentra en el resultado o producto");

        List<Characteristic> returnList = new ArrayList<Characteristic>();
        returnList.add(characteristic);
        when(characteristicServices.getAllCharacteristics()).thenReturn(new ArrayList<Characteristic>());

        returnList = characteristicService.getAllCharacteristics();

        verify(characteristicService, times(1)).getAllCharacteristics();
    }
}
