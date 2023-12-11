package co.com.crud.requirement.domain.service;


import co.com.crud.requirement.domain.model.TypeErrorCharacteristic;
import co.com.crud.requirement.domain.repository.TypeErrorCharacteristicDomainRepository;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
public class TyperErrorCharacteristicServiceTest {

    @MockBean
    TypeErrorCharacteristicDomainRepository typeErrorDomainRepository;
    @Test
    public void testGetAllTypeErrorWhenResultIsNotNull() {
        //Arrange
        TypeErrorCharacteristicDomainRepository mockTypeErrorRepository = mock(TypeErrorCharacteristicDomainRepository.class);
        TypeErrorCharacteristic typeErrorCharacteristic = new TypeErrorCharacteristic();
        typeErrorCharacteristic.setTypeErrorId(1);
        typeErrorCharacteristic.setName("EIE");
        typeErrorCharacteristic.setDescription("Especificación incompleta o errónea");

        ArrayList<TypeErrorCharacteristic> mockTypeErrors = new ArrayList<TypeErrorCharacteristic>();
        mockTypeErrors.add(typeErrorCharacteristic);
        when(mockTypeErrorRepository.getAllTypesErrors()).thenReturn(mockTypeErrors);
        TypeErrorCharacteristicService typeErrorCharacteristicService = new TypeErrorCharacteristicService(mockTypeErrorRepository);

        //Act
        List<TypeErrorCharacteristic> result = typeErrorCharacteristicService.getAllTypesErrors();

        //ASSERT
        verify(mockTypeErrorRepository, times(1)).getAllTypesErrors();
        assertNotNull(result);
    }
}
