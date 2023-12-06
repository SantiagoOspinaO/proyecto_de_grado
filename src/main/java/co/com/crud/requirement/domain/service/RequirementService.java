package co.com.crud.requirement.domain.service;

import co.com.crud.requirement.domain.exception.RequirementNotFoundException;
import co.com.crud.requirement.domain.model.Requirement;
import co.com.crud.requirement.domain.repository.RequirementDomainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static co.com.crud.requirement.domain.exception.validation.DomainValidator.*;

@Service
public class RequirementService {

    private final RequirementDomainRepository requirementDomainRepository;

    @Autowired
    public RequirementService(RequirementDomainRepository requirementDomainRepository) {
        this.requirementDomainRepository = requirementDomainRepository;
    }

    public Requirement saveRequirement(Requirement requirement) {
        validateMandatory(requirement.getName(), NAME_FIELD_MANDATORY);
        validateMandatory(requirement.getDescription(), DESCRIPTION_FIELD_MANDATORY);
        validateMandatory(requirement.getTypeRequirement(), TYPE_FIELD_MANDATORY);
        validateMinMaxLength(requirement.getName(), 5, 50, MAX_MIN_NANE_LENGHT_MESSAGE);

        return requirementDomainRepository.saveRequirement(requirement);
    }

    public List<Requirement> getAllRequirements() {
        return requirementDomainRepository.getAllRequirements();
    }

    public Optional<Requirement> getRequirementById(Integer id) {
        Optional<Requirement> requirements = requirementDomainRepository.getRequirementById(id);
        if (requirements.isEmpty()) {
            throw new RequirementNotFoundException(id);
        }
        return requirements;
    }

    public boolean deleteRequirement(Integer requirementId){
        return getRequirementById(requirementId).map(requirement -> {
            requirementDomainRepository.deleteRequirement(requirementId);

            return true;
        }).orElse(false);
    }
}