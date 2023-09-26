package co.com.crud.requirement.persistence;

import co.com.crud.requirement.domain.model.Requirement;
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

    private final IRequirementCrudRepository IRequirementCrudRepository;
    private final RequirementMapper requirementMapper;

    @Autowired
    public RequirementRepository(IRequirementCrudRepository IRequirementCrudRepository, RequirementMapper requirementMapper) {
        this.IRequirementCrudRepository = IRequirementCrudRepository;
        this.requirementMapper = requirementMapper;
    }

    @Override
    public Requirement saveRequirement(Requirement requirement) {
        RequirementEntity requirementEntity = requirementMapper.toRequirements(requirement);
        return requirementMapper.toRequirement(IRequirementCrudRepository.save(requirementEntity));
    }

    @Override
    public List<Requirement> getAllRequirements() {
        List<RequirementEntity> requirementEntities = (List<RequirementEntity>) IRequirementCrudRepository.findAll();
        return requirementMapper.toRequirementsEntity(requirementEntities);
    }

    @Override
    public Optional<Requirement> getRequirementById(Integer id) {
        return IRequirementCrudRepository.findById(id).map(requirementMapper::toRequirement);
    }

    @Override
    public Optional<List<Requirement>> getRequirementByType(String type) {
        return Optional.empty();
    }

    @Override
    public void deleteRequirement(Integer id) {
        IRequirementCrudRepository.deleteById(id);
    }

}