package com.crud.democrud.services;

import com.crud.democrud.models.RequisitoModel;
import com.crud.democrud.repositories.RequisitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RequisitoService {
    @Autowired
    RequisitoRepository requisitoRepository;
    
    public ArrayList<RequisitoModel> obtenerRequisitos(){
        return (ArrayList<RequisitoModel>) requisitoRepository.findAll();
    }

    public RequisitoModel guardarRequisito(RequisitoModel requisito){

        return requisitoRepository.save(requisito);
    }

    public Optional<RequisitoModel> obtenerPorId(Long id){

        return requisitoRepository.findById(id);
    }

    public ArrayList<RequisitoModel> obtenerPorPrioridad(Integer Id) {
        return requisitoRepository.findById(Id);
    }

    public boolean eliminarRequisito(Long id) {
        try{
            requisitoRepository.deleteById(id);
            return true;
        }catch(Exception error){
            return false;
        }
    }
}