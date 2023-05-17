package com.crud.democrud.controllers;

import com.crud.democrud.models.RequisitoModel;
import com.crud.democrud.services.RequisitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/requisitos")
public class RequisitoController {

    private final RequisitoService requisitoService;

    @Autowired
    public RequisitoController(RequisitoService requisitoService) {
        this.requisitoService = requisitoService;
    }

    @PostMapping()
    public ResponseEntity<RequisitoModel> guardarRequisito(@RequestBody RequisitoModel requisito) {
        //return ResponseEntity.status(HttpStatus.CREATED).body(this.requisitoService.guardarRequisito(requisito).getBody());
        return new ResponseEntity<>(this.requisitoService.guardarRequisito(requisito), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<RequisitoModel>> obtenerRequisitos() {
        //return requisitoService.obtenerRequisitos();
        return ResponseEntity.ok(requisitoService.obtenerRequisitos());
    }

    @GetMapping(path = "/{id}")
    public List<RequisitoModel> obtenerRequisitoPorId(@PathVariable("id") Integer id) {
        return this.requisitoService.obtenerRequisitoPorId(id);
    }

    @PutMapping(path = "/{id}")
    public RequisitoModel actualizarRequisito(@RequestBody RequisitoModel requisito, @PathVariable("id") Integer id) {
        requisito.setId(id);
        return this.requisitoService.guardarRequisito(requisito);
        //return .ok
    }

    @DeleteMapping(path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id) {
        boolean validarRequisito = this.requisitoService.eliminarRequisito(id);
        if (validarRequisito) {
            return "Se eliminó el requisito con Id " + id;
        } else {
            return "No se pudo eliminar el requisito con Id " + id + " porque no se encuentra en la base de datos";
        }
        //return .ok
    }
}