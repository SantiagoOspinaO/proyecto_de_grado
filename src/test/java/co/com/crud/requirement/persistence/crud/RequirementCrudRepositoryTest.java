package co.com.crud.requirement.persistence.crud;

import co.com.crud.requirement.persistence.RequirementRepository;
import co.com.crud.requirement.persistence.entity.RequirementEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;


@SpringBootTest
class RequirementCrudRepositoryTest {

    @Autowired
    private IRequirementCrudRepository requirementCrudRepository;

    @Test
    void testFindRequirementById() {
        //Arrange
        RequirementRepository requirementRepository = mock(RequirementRepository.class);
        IRequirementCrudRepository iRequirementCrudRepository = mock(IRequirementCrudRepository.class);
        when(iRequirementCrudRepository.findById(anyInt())).thenReturn(Optional.empty());

        Integer requirementId = 1;

        //Act
        Optional<RequirementEntity> result = iRequirementCrudRepository.findById(requirementId);

        //Assert
        verify(iRequirementCrudRepository, times(1)).findById(eq(requirementId));
        assertTrue(result.isEmpty());
    }

    @Test
    void testCountRequirementsByFilterCauseError() {
        //Arrange
        int requisitoId = 5;
        int projectId = 1;
        IRequirementCrudRepository iRequirementCrudRepository = mock(IRequirementCrudRepository.class);

        //Act
        iRequirementCrudRepository.countRequirementsByFilterCauseError(requisitoId, projectId);

        //Assert
        verify(iRequirementCrudRepository, (times(1))).countRequirementsByFilterCauseError(requisitoId, projectId);
    }

    @Test
    void testCountRequirementsByGradeAndCauseError() {
        //Arrange
        String tipoRequisito = "No funcional";
        String causeError = "EIE";
        int projectId = 2;
        IRequirementCrudRepository iRequirementCrudRepository = mock(IRequirementCrudRepository.class);

        //Act
        iRequirementCrudRepository.countRequirementsByGradeAndCauseError(tipoRequisito, causeError, projectId);

        //Assert
        verify(iRequirementCrudRepository, times(1)).countRequirementsByGradeAndCauseError(tipoRequisito, causeError, projectId);
    }

}
