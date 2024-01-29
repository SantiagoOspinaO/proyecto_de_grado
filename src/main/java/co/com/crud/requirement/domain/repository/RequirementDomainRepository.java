package co.com.crud.requirement.domain.repository;

import co.com.crud.requirement.domain.model.Requirement;
import co.com.crud.requirement.domain.model.queryresult.IPerfectOrNotPerfectRequirement;
import co.com.crud.requirement.domain.model.queryresult.IRequirementByGradeAndCauseError;
import co.com.crud.requirement.domain.model.queryresult.IRequirementsByFilterCauseError;

import java.util.List;
import java.util.Optional;

public interface RequirementDomainRepository {

    Requirement saveRequirement(Requirement requirement);

    List<Requirement> getAllRequirements();

    Optional<Requirement> getRequirementById(Integer id);

    Optional<List<Requirement>> getRequirementByType(String type);

    void deleteRequirement(Integer requirementId);

    IRequirementsByFilterCauseError countRequirementsByFilterCauseError(Integer requirementId, Integer projectId);

    IRequirementByGradeAndCauseError countRequirementsByGradeAndCauseError(String typeRequirement, String causeError, Integer projectId);

    IPerfectOrNotPerfectRequirement countPerfectRequirements(String typeRequirement, Integer projectId);

    List<Requirement> getRequirementsByProyectoId(Integer projectId);

    int countAllRequirements(String typeRequirement, Integer projectId);

    void updateQualifiedByRequirementId(Integer requirementId);

    void updateProjectStatus(Integer projectId);

}