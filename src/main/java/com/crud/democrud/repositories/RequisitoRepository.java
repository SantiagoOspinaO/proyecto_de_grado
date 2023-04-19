package com.crud.democrud.repositories;

import com.crud.democrud.models.RequisitoModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface RequisitoRepository extends CrudRepository<RequisitoModel, Long> {
    public abstract ArrayList<RequisitoModel> findById(Integer id);
}