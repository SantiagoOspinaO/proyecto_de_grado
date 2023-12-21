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

    @GetMapping(path = "/count-type-requirement-cause-error")
    public int countRequirementsByTypeAndCauseError(@RequestParam(required = false) String typeRequirement, @RequestParam String causeError) {
        return typeErrorCharacteristicService.countRequirementsByTypeAndCauseError(typeRequirement, causeError);
    }

    @GetMapping(path = "/get-cause-errors")
    public int countRequirementsByCauseErrorAndRequirementId(@RequestParam(required = false) Integer requirementId, @RequestParam String causeError) {
        return typeErrorCharacteristicService.countRequirementsByCauseErrorAndRequirementId(requirementId, causeError);
    }

    @GetMapping(path = "/get-cause-error-all-requirement/dde")
    public int countRequirementsByErrorDDE() {
        return typeErrorCharacteristicService.countRequirementsByCauseErrorDDE();
    }

    @GetMapping(path = "/get-cause-error-all-requirement/dii")
    public int countRequirementsByErrorDII() {
        return typeErrorCharacteristicService.countRequirementsByCauseErrorDII();
    }

    @GetMapping(path = "/get-cause-error-all-requirement/var")
    public int countRequirementsByErrorVAR() {
        return typeErrorCharacteristicService.countRequirementsByCauseErrorVAR();
    }

    @GetMapping(path = "/EIE/{id}")
    public int countTypeErrorEIEByRequirement(@PathVariable("id") Integer requirementId) {
        return typeErrorCharacteristicService.countTypeErrorEIEByRequirement(requirementId);
    }

    @GetMapping(path = "/MCC/{id}")
    public int countTypeErrorMCCByRequirement(@PathVariable("id") Integer requirementId) {
        return typeErrorCharacteristicService.countTypeErrorMCCByRequirement(requirementId);
    }
    // Los 2 de arriba pero dinamicos
    @GetMapping(path = "/{typeErrorId}/{id}")
    public int countTypeErrorsByRequirements(@PathVariable("typeErrorId") Integer typeErrorId, @PathVariable("id") Integer requirementId) {
        return typeErrorCharacteristicService.countTypeErrorsByRequirements(typeErrorId, requirementId);
    }

    @GetMapping(path = "/allTypeErrors/{id}")
    public int countAllTypeErrorsByRequirement(@PathVariable("id") Integer requirementId) {
        return typeErrorCharacteristicService.countAllTypeErrorsByRequirement(requirementId);
    }

    @GetMapping(path = "/allCauseErrors/{id}")
    public int countCauseErrorsByRequirement(@PathVariable("id") Integer requirementId) {
        return typeErrorCharacteristicService.countAllCauseErrorsByRequirement(requirementId);
    }

    @GetMapping(path = "/percentageOfTypeErrorEIEById/{id}")
    public double percentageOfTypeErrorEIEById(@PathVariable("id") Integer requirementId) {
        return typeErrorCharacteristicService.percentageOfTypeErrorEIEById(requirementId);
    }

    @GetMapping(path = "/percentageOfTypeErrorMCCById/{id}")
    public double percentageOfTypeErrorMCCById(@PathVariable("id") Integer requirementId) {
        return typeErrorCharacteristicService.percentageOfTypeErrorMCCById(requirementId);
    }

    // Es los 2 querys de arriba pero dinamico
    @GetMapping(path = "/percentageOfTypeErrors/{typeErrorID}/{id}")
    public double percentageOfTypeErrorsById(@PathVariable("id") Integer typeErrorID, @PathVariable("id") Integer requirementId) {
        return typeErrorCharacteristicService.percentageOfTypeErrosById(typeErrorID, requirementId);
    }

    @GetMapping(path = "/percentageOfAllTypeErrorById/{id}")
    public double percentageOfAllTypeErrorById(@PathVariable("id") Integer requirementId) {
        return typeErrorCharacteristicService.percentageOffAllTypeErrorById(requirementId);
    }

    @GetMapping(path = "/numberOfCauseErrorById/{id}/{causeError}")
    public Integer numberOfCauseErrorById(@PathVariable("id") Integer requirementId, @PathVariable("causeError") String causeError){
        return typeErrorCharacteristicService.numberOfCauseErrorById(requirementId,causeError);
    }

    @GetMapping(path = "/percentageOfCauseErrorById/{id}/{causeError}")
    public double percentageOfCauseErrorById(@PathVariable("id") Integer requirementId, @PathVariable("causeError") String causeError){
        return typeErrorCharacteristicService.percentageOfCauseErrorById(requirementId,causeError);
    }

}
