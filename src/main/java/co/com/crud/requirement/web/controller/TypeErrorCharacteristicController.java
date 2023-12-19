package co.com.crud.requirement.web.controller;

import co.com.crud.requirement.domain.model.TypeErrorCharacteristic;
import co.com.crud.requirement.domain.service.TypeErrorCharacteristicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/type-errors")
public class TypeErrorCharacteristicController {

    private final TypeErrorCharacteristicService typeErrorCharacteristicService;

    @Autowired
    public TypeErrorCharacteristicController(TypeErrorCharacteristicService typeErrorCharacteristicService) {
        this.typeErrorCharacteristicService = typeErrorCharacteristicService;
    }

    @GetMapping()
    public ResponseEntity<List<TypeErrorCharacteristic>> getAllTypeErrors() {
        return ResponseEntity.ok(typeErrorCharacteristicService.getAllTypesErrors());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<TypeErrorCharacteristic>> getTypeErrorById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(typeErrorCharacteristicService.getTypeErrorById(id));
    }

    @GetMapping(path = "/count-type-requirement-type-error")
    public int countRequirementsByTypeAndTypeError(@RequestParam(required = false) String typeRequirement, @RequestParam String typeError) {
        return typeErrorCharacteristicService.countRequirementsByTypeAndTypeError(typeRequirement, typeError);
    }

    @GetMapping(path = "/get-type-error-all-requirement/dde")
    public int countRequirementsByErrorDDE() {
        return typeErrorCharacteristicService.countRequirementsByErrorDDE();
    }

    @GetMapping(path = "/get-type-error-all-requirement/dii")
    public int countRequirementsByErrorDII() {
        return typeErrorCharacteristicService.countRequirementsByErrorDII();
    }

    @GetMapping(path = "/get-type-error-all-requirement/var")
    public int countRequirementsByErrorVAR() {
        return typeErrorCharacteristicService.countRequirementsByErrorVAR();
    }

    @GetMapping(path = "/EIE/{id}")
    public int countTypeErrorEIEByRequirement(@PathVariable("id") Integer requirementId) {
        return typeErrorCharacteristicService.countTypeErrorEIEByRequirement(requirementId);
    }

    @GetMapping(path = "/MCC/{id}")
    public int countTypeErrorMCCByRequirement(@PathVariable("id") Integer requirementId) {
        return typeErrorCharacteristicService.countTypeErrorMCCByRequirement(requirementId);
    }

    @GetMapping(path = "/all/{id}")
    public int countTypeErrorsByRequirement(@PathVariable("id") Integer requirementId) {
        return typeErrorCharacteristicService.countTypeErrorsByRequirement(requirementId);
    }

    @GetMapping(path = "/percentageOfTypeErrorEIEById/{id}")
    public double percentageOfTypeErrorEIEById(@PathVariable("id") Integer requirementId) {
        return typeErrorCharacteristicService.percentageOfTypeErrorEIEById(requirementId);
    }

    @GetMapping(path = "/percentageOfTypeErrorMCCById/{id}")
    public double percentageOfTypeErrorMCCById(@PathVariable("id") Integer requirementId) {
        return typeErrorCharacteristicService.percentageOfTypeErrorMCCById(requirementId);
    }

    @GetMapping(path = "/percentageOfAllTypeErrorById/{id}")
    public double percentageOfAllTypeErrorById(@PathVariable("id") Integer requirementId) {
        return typeErrorCharacteristicService.percentageOffAllErrorById(requirementId);
    }

}
