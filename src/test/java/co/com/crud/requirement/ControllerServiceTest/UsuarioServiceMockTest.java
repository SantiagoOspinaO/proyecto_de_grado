package co.com.crud.requirement.ControllerServiceTest;

import co.com.crud.requirement.domain.service.RequirementService;
import co.com.crud.requirement.persistence.crud.RequirementCrudRepository;
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
    RequirementCrudRepository requirementCrudRepository;

    @Autowired
    RequirementService requirementService;

    @Test
    public void testRequisitoMock() {
        when(requirementCrudRepository.findAll()).thenReturn(new ArrayList<>());
        assertThat(requirementService.getAllRequirements().isEmpty());
        verify(requirementCrudRepository).findAll();
    }

    public void testRequisitoRolMock() {

    }
}
