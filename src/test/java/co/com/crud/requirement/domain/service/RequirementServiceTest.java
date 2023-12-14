package co.com.crud.requirement.domain.service;


import co.com.crud.requirement.domain.model.Requirement;
import co.com.crud.requirement.domain.repository.RequirementDomainRepository;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
public class RequirementServiceTest {

    @MockBean
    RequirementDomainRepository requirementDomainRepository;
    @Test
    public void testGetAllRequirementsWhenResultIsNotNull() {
        //Arrange
        RequirementDomainRepository mockRequirementRepository = mock(RequirementDomainRepository.class);
        Requirement requirement = new Requirement();
        requirement.setProjectId(1);
        requirement.setRequirementId(1);
        requirement.setName("Requisito prueba unitaria");
        requirement.setDescription("Requisito para ensayar el mock de la prueba unitaria");
        requirement.setTypeRequirement("Funcional");

        ArrayList<Requirement> mockRequirements = new ArrayList<Requirement>();
        mockRequirements.add(requirement);
        when(mockRequirementRepository.getAllRequirements()).thenReturn(mockRequirements);
        RequirementService requirementsService = new RequirementService(mockRequirementRepository);

        //Act
        List<Requirement> result = requirementsService.getAllRequirements();

        //ASSERT
        verify(mockRequirementRepository, times(1)).getAllRequirements();
        assertNotNull(result);
    }

    @Test
    public void testSaveRequirement() {
        //Arrange
        RequirementDomainRepository requirementDomainRepository = mock(RequirementDomainRepository.class);
        RequirementService requirementService = new RequirementService(requirementDomainRepository);
        Requirement requirement = new Requirement();
        requirement.setProjectId(1);
        requirement.setRequirementId(1);
        requirement.setName("Requisito con adecuacion alto-bajo");
        requirement.setDescription("Requisito para medir niveles de adecuacion alto-bajo");
        requirement.setTypeRequirement("Funcional");

        ArrayList<Requirement> mockRequirements = new ArrayList<Requirement>();
        mockRequirements.add(requirement);
        when(requirementDomainRepository.saveRequirement(any())).thenReturn(requirement);

        //Act
        Requirement result = requirementService.saveRequirement(requirement);

        //Assert
        verify(requirementDomainRepository, times(1)).saveRequirement(eq(requirement));
        assertEquals(requirement, result);
    }
}
