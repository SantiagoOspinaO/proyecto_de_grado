package com.crud.democrud.controllers;

import com.crud.democrud.models.RequisitoModel;
import com.crud.democrud.services.RequisitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/requisitos")
public class RequisitoController {
    @Autowired
    RequisitoService requisitoService;

    @GetMapping()
    public ArrayList<RequisitoModel> obtenerUsuarios() {
        return requisitoService.obtenerRequisitos();
    }

    @PostMapping()
    public RequisitoModel guardarRequisito(@RequestBody RequisitoModel requisito) {
        return this.requisitoService.guardarRequisito(requisito);
    }

    @PutMapping(path = "/{id}")
    public RequisitoModel actualizarRequisito(@RequestBody RequisitoModel requisito,@PathVariable("id") Long id) {
        requisito.setId(id);
        return this.requisitoService.guardarRequisito(requisito);
    }

    @GetMapping(path = "/{id}")
    public Optional<RequisitoModel> obtenerRequisitoPorId(@PathVariable("id") Long id) {
        return this.requisitoService.obtenerPorId(id);
    }

    @DeleteMapping(path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id) {
        boolean validarRequisito = this.requisitoService.eliminarRequisito(id);
        if (validarRequisito) {
            return "Se eliminó el requisito con id " + id;
        } else {
            return "No pudo eliminar el requisito con id" + id;
        }
    }
}