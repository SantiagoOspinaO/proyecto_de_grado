package co.com.crud.requirement.domain.repository;

import co.com.crud.requirement.domain.model.TypeErrorCharacteristic;

import java.util.List;
import java.util.Optional;

public interface TypeErrorCharacteristicDomainRepository {

    List<TypeErrorCharacteristic> getAllTypesErrors();

    Optional<TypeErrorCharacteristic> getTypeErrorById(Integer id);
}