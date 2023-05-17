package com.crud.democrud.repositories;

import com.crud.democrud.models.RequirementModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequirementRepository extends CrudRepository<RequirementModel, Long> {

    List<RequirementModel> findById(Integer id);

}