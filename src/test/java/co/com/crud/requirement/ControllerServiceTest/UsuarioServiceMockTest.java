package co.com.crud.requirement.ControllerServiceTest;

import co.com.crud.requirement.domain.service.RequirementService;
import co.com.crud.requirement.persistence.crud.IRequirementCrudRepository;
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
    IRequirementCrudRepository IRequirementCrudRepository;

    @Autowired
    RequirementService requirementService;

    @Test
    public void testRequisitoMock() {
        when(IRequirementCrudRepository.findAll()).thenReturn(new ArrayList<>());
        assertThat(requirementService.getAllRequirements().isEmpty());
        verify(IRequirementCrudRepository).findAll();
    }

    public void testRequisitoRolMock() {

    }
}
