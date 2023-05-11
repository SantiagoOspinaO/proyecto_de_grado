package com.crud.democrud.controllers;

import com.crud.democrud.models.RequisitoModel;
import com.crud.democrud.services.RequisitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping("/requisitos")
public class RequisitoController {

    @Autowired
    RequisitoService requisitoService;

    @PostMapping()
    public RequisitoModel guardarRequisito(@RequestBody RequisitoModel requisito) {
        return this.requisitoService.guardarRequisito(requisito).getBody();
    }

    @GetMapping()
    public ArrayList<RequisitoModel> obtenerRequisitos() {
        return requisitoService.obtenerRequisitos();
    }

    @GetMapping(path = "/{id}")
    public ArrayList<RequisitoModel> obtenerRequisitoPorId(@PathVariable("id") Integer id) {
        return this.requisitoService.obtenerRequisitoPorId(id);
    }

    @PutMapping(path = "/{id}")
    public RequisitoModel actualizarRequisito(@RequestBody RequisitoModel requisito, @PathVariable("id") Integer id) {
        requisito.setId(id);
        return this.requisitoService.guardarRequisito(requisito).getBody();
    }

    @DeleteMapping(path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id) {
        boolean validarRequisito = this.requisitoService.eliminarRequisito(id);
        if (validarRequisito) {
            return "Se eliminó el requisito con Id " + id;
        } else {
            return "No se pudo eliminar el requisito con Id " + id + " porque no se encuentra en la base de datos";
        }
    }
}