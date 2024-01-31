package co.com.crud.requirement.persistence;

import co.com.crud.requirement.domain.model.Requirement;
import co.com.crud.requirement.domain.model.queryresult.IPerfectOrNotPerfectRequirement;
import co.com.crud.requirement.domain.model.queryresult.IRequirementByGradeAndCauseError;
import co.com.crud.requirement.domain.model.queryresult.IRequirementsByFilterCauseError;
import co.com.crud.requirement.domain.model.queryresult.ITypeConsultingProject;
import co.com.crud.requirement.domain.repository.RequirementDomainRepository;
import co.com.crud.requirement.persistence.crud.IRequirementCrudRepository;
import co.com.crud.requirement.persistence.entity.RequirementEntity;
import co.com.crud.requirement.persistence.mapper.RequirementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RequirementRepository implements RequirementDomainRepository {

    private final IRequirementCrudRepository requirementCrudRepository;
    private final RequirementMapper requirementMapper;

    @Autowired
    public RequirementRepository(IRequirementCrudRepository requirementCrudRepository, RequirementMapper requirementMapper) {
        this.requirementCrudRepository = requirementCrudRepository;
        this.requirementMapper = requirementMapper;
    }

    @Override
    public Requirement saveRequirement(Requirement requirement) {
        RequirementEntity requirementEntity = requirementMapper.toRequirements(requirement);
        return requirementMapper.toRequirement(requirementCrudRepository.save(requirementEntity));
    }

    @Override
    public List<Requirement> getAllRequirements() {
        List<RequirementEntity> requirementEntities = (List<RequirementEntity>) requirementCrudRepository.findAll();
        return requirementMapper.toRequirementsEntity(requirementEntities);
    }

    @Override
    public Optional<Requirement> getRequirementById(Integer id) {
        return requirementCrudRepository.findById(id).map(requirementMapper::toRequirement);
    }

    @Override
    public Optional<List<Requirement>> getRequirementByType(String type) {
        return Optional.empty();
    }

    @Override
    public void deleteRequirement(Integer id) {
        requirementCrudRepository.deleteById(id);
    }

    @Override
    public IRequirementsByFilterCauseError countRequirementsByFilterCauseError(Integer requirementId, Integer projectId) {
        return requirementCrudRepository.countRequirementsByFilterCauseError(requirementId, projectId);
    }

    @Override
    public IRequirementByGradeAndCauseError countRequirementsByGradeAndCauseError(String typeRequirement, String causeError, Integer projectId) {
        return requirementCrudRepository.countRequirementsByGradeAndCauseError(typeRequirement, causeError, projectId);
    }

    @Override
    public IPerfectOrNotPerfectRequirement countPerfectRequirements(String typeRequirement, Integer projectId) {
        return requirementCrudRepository.countPerfectRequirements(typeRequirement, projectId);
    }

    @Override
    public List<Requirement> getRequirementsByProyectoId(Integer proyectoId) {
        List<RequirementEntity> requirementEntities = requirementCrudRepository.getRequirementsByProyectoId(proyectoId);
        return requirementMapper.toRequirementsEntity(requirementEntities);
    }

    @Override
    public int countAllRequirements(String typeRequirement, Integer projectId) {
        return requirementCrudRepository.countAllRequirements(typeRequirement, projectId);
    }

    @Override
    public void updateQualifiedByRequirementId(Integer requirementId) {
        requirementCrudRepository.updateQualifiedByRequirementId(requirementId);
    }

    @Override
    public void updateProjectStatus(Integer projectId) {
        requirementCrudRepository.updateProjectStatus(projectId);
    }

    @Override
    public void deleteDataRoleSelection(Integer projectId) {
        requirementCrudRepository.deleteDataRoleSelection(projectId);
    }

    @Override
    public void deleteDataRoleUser(Integer projectId) {
        requirementCrudRepository.deleteDataRoleUser(projectId);
    }

    @Override
    public ITypeConsultingProject getTypeOfConsulting(Integer projectId) {
        return requirementCrudRepository.getTypeOfConsulting(projectId);
    }

}