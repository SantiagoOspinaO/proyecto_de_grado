package co.com.crud.requirement.domain.repository;

import co.com.crud.requirement.domain.model.Requirement;
import co.com.crud.requirement.persistence.crud.interfaces.IRequirementByGradeAndCauseErrorDTO;
import co.com.crud.requirement.persistence.crud.interfaces.IRequirementsByFilterCauseErrorDTO;

import java.util.List;
import java.util.Optional;

public interface RequirementDomainRepository {

    Requirement saveRequirement(Requirement requirement);

    List<Requirement> getAllRequirements();

    Optional<Requirement> getRequirementById(Integer id);

    Optional<List<Requirement>> getRequirementByType(String type);

    void deleteRequirement(Integer requirementId);

    IRequirementsByFilterCauseErrorDTO countRequirementsByFilterCauseError(Integer requirementId);

    IRequirementByGradeAndCauseErrorDTO countRequirementsByGradeAndCauseError(String typeRequirement, String causeError, Integer projectId);

    IRequirementsByFilterCauseErrorDTO countCauseErrorByRequirementType(String typeRequirement);
}