package co.com.crud.requirement.ControllerServiceTest;

import co.com.crud.requirement.repository.RequirementRepository;
import co.com.crud.requirement.service.RequirementService;
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
    RequirementRepository requirementRepository;

    @Autowired
    RequirementService requirementService;

    @Test
    public void testRequisitoMock() {
        when(requirementRepository.findAll()).thenReturn(new ArrayList<>());
        assertThat(requirementService.getAllRequirements().isEmpty());
        verify(requirementRepository).findAll();
    }

    public void testRequisitoRolMock() {

    }
}
