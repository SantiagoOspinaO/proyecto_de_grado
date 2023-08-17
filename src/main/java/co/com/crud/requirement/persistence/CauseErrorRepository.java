package co.com.crud.requirement.persistence;

import co.com.crud.requirement.domain.model.CauseError;
import co.com.crud.requirement.domain.repository.CauseErrorDomainRepository;
import co.com.crud.requirement.persistence.crud.CauseErrorCrudRepository;
import co.com.crud.requirement.persistence.entity.CauseErrorEntity;
import co.com.crud.requirement.persistence.mapper.CauseErrorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CauseErrorRepository implements CauseErrorDomainRepository {
    private final CauseErrorCrudRepository causeErrorCrudRepository;

    private final CauseErrorMapper causeErrorMapper;

    @Autowired
    public CauseErrorRepository(CauseErrorCrudRepository causeErrorCrudRepository, CauseErrorMapper causeErrorMapper) {
        this.causeErrorCrudRepository = causeErrorCrudRepository;
        this.causeErrorMapper = causeErrorMapper;
    }

    @Override
    public List<CauseError> getAllCauseErrors() {
        List<CauseErrorEntity> causeErrorEntities = (List<CauseErrorEntity>) causeErrorCrudRepository.findAll();
        return causeErrorMapper.toCauseErrorsEntity(causeErrorEntities);
    }

    @Override
    public Optional<CauseError> getCauseErrorById(Integer id) {
        return causeErrorCrudRepository.findById(id).map(causeErrorMapper::toCauseError);
    }
}
