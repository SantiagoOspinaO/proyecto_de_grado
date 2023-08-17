package co.com.crud.requirement.domain.repository;

import co.com.crud.requirement.domain.model.Mistake;

import java.util.List;
import java.util.Optional;

public interface MistakeDomainRepository {

    List<Mistake> getAllMistakes();

    Optional<Mistake> getMistakeById(Integer id);
}
