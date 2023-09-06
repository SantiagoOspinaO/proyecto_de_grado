package co.com.crud.requirement.domain.repository;

import co.com.crud.requirement.domain.model.Characteristic;

import java.util.List;
import java.util.Optional;

public interface CharacteristicDomainRepository {

    List<Characteristic> getAllCharacteristics();

    Optional<Characteristic> getCharacteristicById(Integer id);

}
