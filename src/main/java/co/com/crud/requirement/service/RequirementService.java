package co.com.crud.requirement.service;

import co.com.crud.requirement.repository.RequirementRepository;
import co.com.crud.requirement.exception.domain.NotFoundException;
import co.com.crud.requirement.model.RequirementModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequirementService {

    private final RequirementRepository requirementRepository;

    @Autowired
    public RequirementService(RequirementRepository requirementRepository) {
        this.requirementRepository = requirementRepository;
    }

    public RequirementModel saveRequirement(RequirementModel requirement) {

        if (requirement.getNombre() == null || requirement.getNombre().isEmpty()) {
            throw new RuntimeException("¡ERROR! El campo nombre no es válido.");
        }
        return requirementRepository.save(requirement);
    }

    public List<RequirementModel> getAllRequirements() {
        return (List<RequirementModel>) requirementRepository.findAll();
    }

    public List<RequirementModel> getRequirementById(Integer id) {
        if (requirementRepository.findById(id).isEmpty()) {
            throw new NotFoundException("¡ERROR! El requisito no se encontró en la base de datos.");
        }
        return requirementRepository.findById(id);
    }

}