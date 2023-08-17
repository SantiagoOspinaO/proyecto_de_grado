package co.com.crud.requirement.domain.service;

import co.com.crud.requirement.domain.exception.CauseErrorNotFoundException;
import co.com.crud.requirement.domain.model.CauseError;
import co.com.crud.requirement.domain.repository.CauseErrorDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CauseErrorService {

    private final CauseErrorDomainRepository causeErrorDomainRepository;

    @Autowired
    public CauseErrorService(CauseErrorDomainRepository causeErrorDomainRepository) {
        this.causeErrorDomainRepository = causeErrorDomainRepository;
    }

    public List<CauseError> getAllCauseErrors(){
        return causeErrorDomainRepository.getAllCauseErrors();
    }

    public Optional<CauseError> getCauseErrorsById(Integer id) {
        Optional<CauseError> causeErrors = causeErrorDomainRepository.getCauseErrorById(id);
        if(causeErrors.isEmpty()) {
            throw new CauseErrorNotFoundException(id);
        }
        return causeErrors;
    }
}
