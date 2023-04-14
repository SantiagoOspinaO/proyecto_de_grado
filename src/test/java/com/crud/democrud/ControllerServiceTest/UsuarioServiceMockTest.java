package com.crud.democrud.ControllerServiceTest;

import com.crud.democrud.repositories.RequisitoRepository;
import com.crud.democrud.services.RequisitoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UsuarioServiceMockTest {

    @MockBean
    RequisitoRepository requisitoRepository;

    @Autowired
    RequisitoService requisitoService;

    @Test
    public void testRequisitoMock() {
        when(requisitoRepository.findAll()).thenReturn(new ArrayList<>());
        assertThat(requisitoService.obtenerRequisitos().isEmpty());
        verify(requisitoRepository).findAll();
    }

    public void testRequisitoRolMock() {

    }
}
