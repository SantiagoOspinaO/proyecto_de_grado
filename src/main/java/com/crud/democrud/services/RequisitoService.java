package com.crud.democrud.services;

import com.crud.democrud.models.RequisitoModel;
import com.crud.democrud.repositories.RequisitoRepository;
import com.crud.democrud.utils.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequisitoService {


    private final RequisitoRepository requisitoRepository;

    @Autowired
    public RequisitoService(RequisitoRepository requisitoRepository) {
        this.requisitoRepository = requisitoRepository;
    }

    public RequisitoModel guardarRequisito(RequisitoModel requisito) {

        if (requisito.getNombre() == null || requisito.getNombre().isEmpty()) {
            throw new RuntimeException("¡ERROR! El campo nombre no es válido.");
        }
        requisitoRepository.save(requisito);
        return ResponseEntity.ok(requisito).getBody();
    }

    public List<RequisitoModel> obtenerRequisitos() {
        return (ArrayList<RequisitoModel>) requisitoRepository.findAll();
    }

    public List<RequisitoModel> obtenerRequisitoPorId(Integer id) {
        if (requisitoRepository.findById(id).isEmpty()) {
            throw new NotFoundException("¡ERROR! El requisito no se encontró en la base de datos.");
        }
        return requisitoRepository.findById(id);
    }

    /*
    public ArrayList<RequisitoModel> obtenerPorPrioridad(Integer Id) {
        return requisitoRepository.findByPriority(Id);
    }*/

    public boolean eliminarRequisito(Long id) {
        try {
            requisitoRepository.deleteById(id);
            return true;
        } catch (Exception error) {
            return false;
        }
    }
}