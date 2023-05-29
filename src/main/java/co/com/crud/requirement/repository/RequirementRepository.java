package co.com.crud.requirement.repository;

import co.com.crud.requirement.model.RequirementModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequirementRepository extends CrudRepository<RequirementModel, Long> {

    List<RequirementModel> findById(Integer id);

}