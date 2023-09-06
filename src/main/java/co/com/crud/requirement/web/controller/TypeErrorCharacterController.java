package co.com.crud.requirement.web.controller;

import co.com.crud.requirement.domain.model.TypeErrorCharacteristic;
import co.com.crud.requirement.domain.service.TypeErrorCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/type-errors")
public class TypeErrorCharacterController {

    private final TypeErrorCharacterService typeErrorCharacterService;

    @Autowired
    public TypeErrorCharacterController(TypeErrorCharacterService typeErrorCharacterService) {
        this.typeErrorCharacterService = typeErrorCharacterService;
    }

    @GetMapping()
    public ResponseEntity<List<TypeErrorCharacteristic>> getAllTypeErrors() {
        return ResponseEntity.ok(typeErrorCharacterService.getAllTypesErrors());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<TypeErrorCharacteristic>> getTypeErrorById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(typeErrorCharacterService.getTypeErrorById(id));
    }
}
