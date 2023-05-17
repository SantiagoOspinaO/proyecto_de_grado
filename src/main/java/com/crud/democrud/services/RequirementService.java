package com.crud.democrud.services;

import com.crud.democrud.models.RequirementModel;
import com.crud.democrud.repositories.RequirementRepository;
import com.crud.democrud.utils.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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