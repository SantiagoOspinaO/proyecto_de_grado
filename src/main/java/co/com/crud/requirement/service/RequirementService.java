package co.com.crud.requirement.service;

import co.com.crud.requirement.dto.RequirementDTO;
import co.com.crud.requirement.exception.domain.RequirementNotFoundException;
import co.com.crud.requirement.exception.domain.validation.DomainValidator;
import co.com.crud.requirement.model.RequirementModel;
import co.com.crud.requirement.repository.RequirementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static co.com.crud.requirement.exception.domain.validation.DomainValidator.*;
import static org.hibernate.persister.entity.EntityPersister.ENTITY_ID;

@Service
public class RequirementService {
    private final RequirementRepository requirementRepository;

    @Autowired
    public RequirementService(RequirementRepository requirementRepository) {
        this.requirementRepository = requirementRepository;
    }

    public RequirementModel saveRequirement(RequirementModel requirement) {
        validateMandatory(requirement.getNombre(), NAME_FIELD_MANDATORY);
        validateMandatory(requirement.getDescripcion(), DESCRIPTION_FIELD_MANDATORY);
        validateMandatory(requirement.getTipoRequisito(), TYPE_FIELD_MANDATORY);
        validateMinMaxLength(requirement.getNombre(), 5, 50, MAX_MIN_NANE_LENGHT_MESSAGE);

        return requirementRepository.save(requirement);
    }

    public List<RequirementModel> getAllRequirements() {
        return (List<RequirementModel>) requirementRepository.findAll();
    }

    public List<RequirementModel> getRequirementById(Integer id) {
        List<RequirementModel> requirements = requirementRepository.findById(id);
        if (requirements == null || requirements.isEmpty()) {
            throw new RequirementNotFoundException(id);
        }
        return requirements;
    }

}