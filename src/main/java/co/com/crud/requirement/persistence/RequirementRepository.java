package co.com.crud.requirement.persistence;

import co.com.crud.requirement.domain.model.Requirement;
import co.com.crud.requirement.domain.repository.RequirementDomainRepository;
import co.com.crud.requirement.persistence.crud.IMCCAndEIEStatisticsDTO;
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
    public IMCCAndEIEStatisticsDTO countRequirementsByFilterCauseError(Integer requirementId) {
        return requirementCrudRepository.countRequirementsByFilterCauseError(requirementId);
    }

}