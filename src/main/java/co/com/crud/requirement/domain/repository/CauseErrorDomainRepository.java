package co.com.crud.requirement.domain.repository;

import co.com.crud.requirement.domain.model.CauseError;
import co.com.crud.requirement.domain.model.Character;

import java.util.List;
import java.util.Optional;

public interface CauseErrorDomainRepository {

    List<CauseError> getAllCauseErrors();

    Optional<CauseError> getCauseErrorById(Integer id);
}
