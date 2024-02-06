package co.com.crud.requirement.web.controller;

import co.com.crud.requirement.domain.model.queryresult.ICharacteristicsByRequirementId;
import co.com.crud.requirement.persistence.crud.ICharacteristicCrudRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CharacteristicRepositoryTest {
    @Autowired
    private ICharacteristicCrudRepository characteristicCrudRepository;

    @Test
    void testFindCharacteristicsByRequirementId() {
        Integer requirementId = 1;

        List<ICharacteristicsByRequirementId> characteristics =
                characteristicCrudRepository.findCharacteristicsByRequirementId(requirementId);

        assertNotNull(characteristics);
        assertFalse(characteristics.isEmpty());
    }
}
