package co.com.crud.requirement.persistence.crud;

import co.com.crud.requirement.persistence.RequirementRepository;
import co.com.crud.requirement.persistence.entity.RequirementEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RequirementCrudRepositoryTest {

    @Autowired
    private IRequirementCrudRepository requirementCrudRepository;

    @Test
    public void testFindRequirementById() {
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
}
