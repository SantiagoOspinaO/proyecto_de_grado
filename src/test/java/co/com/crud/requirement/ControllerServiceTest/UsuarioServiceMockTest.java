package co.com.crud.requirement.ControllerServiceTest;

import co.com.crud.requirement.domain.model.Requirement;
import co.com.crud.requirement.domain.service.RequirementService;
import co.com.crud.requirement.persistence.crud.IRequirementCrudRepository;
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

    @Test
    void testTodosRequisitos() {
        RequirementService requirementService1 = Mockito.mock(RequirementService.class);
        Requirement requirement = new Requirement();
        requirement.setProjectId(1);
        requirement.setName("Ensayo");
        requirement.setRequirementId(1);
        requirement.setTypeRequirement("Funcional");
        requirement.setDescription("Ensayo sonnar");
        requirement.setQualified(true);

        List<Requirement> aRetunrList = new ArrayList<Requirement>();
        aRetunrList.add(requirement);
        when(requirementService.getAllRequirements()).thenReturn(new ArrayList<Requirement>());

        aRetunrList = requirementService1.getAllRequirements();

        verify(requirementService1, times(1)).getAllRequirements();
    }

}
