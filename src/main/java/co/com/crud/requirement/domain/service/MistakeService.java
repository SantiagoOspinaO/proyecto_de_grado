package co.com.crud.requirement.domain.service;

import co.com.crud.requirement.domain.exception.MistakeNotFoundException;
import co.com.crud.requirement.domain.model.Mistake;
import co.com.crud.requirement.domain.repository.MistakeDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class MistakeService {

    private final MistakeDomainRepository mistakeDomainRepository;

    @Autowired
    public MistakeService(MistakeDomainRepository mistakeDomainRepository) {
        this.mistakeDomainRepository = mistakeDomainRepository;
    }

    public List<Mistake> getAllMistakes() {
        return mistakeDomainRepository.getAllMistakes();
    }

    public Optional<Mistake> getMistakeById(Integer id) {
        Optional<Mistake> mistakes = mistakeDomainRepository.getMistakeById(id);
        if(mistakes.isEmpty()) {
            throw new MistakeNotFoundException(id);
        }
        return mistakes;
    }
}
