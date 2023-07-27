package co.com.crud.requirement.persistence;

import co.com.crud.requirement.domain.model.Requirement;
import co.com.crud.requirement.domain.repository.RequirementDomainRepository;
import co.com.crud.requirement.persistence.crud.RequirementCrudRepository;
import co.com.crud.requirement.persistence.entity.RequirementEntity;
import co.com.crud.requirement.persistence.mapper.RequirementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class RequirementRepository implements RequirementDomainRepository {

    private final RequirementCrudRepository requirementCrudRepository;
    private final RequirementMapper requirementMapper;

    @Autowired
    public RequirementRepository(RequirementCrudRepository requirementCrudRepository, RequirementMapper requirementMapper) {
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

//    @Override
//    public Optional<Requirement> getRequirementById(Integer id) {
//        List<RequirementEntity> requirementEntity = requirementCrudRepository.findById(id);
//        if (requirementEntity.isEmpty()) {
//            return Optional.of(requirementMapper.toRequirement(requirementEntity.get(id)));
//        } else {
//            return Optional.empty();
//        }
//    }

    @Override
    public Optional<Requirement> getRequirementById(Integer id) {
        return requirementCrudRepository.findById(id).map(requirement -> requirementMapper.toRequirement(requirement));
    }

    @Override
    public Optional<List<Requirement>> getRequirementByType(String type) {
        return Optional.empty();
    }
}