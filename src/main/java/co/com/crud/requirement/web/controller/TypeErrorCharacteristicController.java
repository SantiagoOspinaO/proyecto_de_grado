package co.com.crud.requirement.web.controller;

import co.com.crud.requirement.domain.model.TypeErrorCharacteristic;
import co.com.crud.requirement.domain.model.queryresult.IErrorDistributionAllRequirements;
import co.com.crud.requirement.domain.model.queryresult.IRequirementsByTypeAndCauseError;
import co.com.crud.requirement.domain.service.TypeErrorCharacteristicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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

    @GetMapping(path = "/get-cause-errors")
    public int countRequirementsByCauseErrorAndRequirementId(
            @RequestParam(required = false) Integer requirementId,
            @RequestParam Integer typeErrorId,
            @RequestParam String causeError
    ) {
        return typeErrorCharacteristicService.countRequirementsByCauseErrorAndRequirementId(requirementId, typeErrorId, causeError);
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

    @GetMapping(path = "/eie/{id}")
    public int countTypeErrorEIEByRequirement(@PathVariable("id") Integer requirementId) {
        return typeErrorCharacteristicService.countTypeErrorEIEByRequirement(requirementId);
    }

    @GetMapping(path = "/eie/dde/{id}")
    public int countTypeErrorEIEAndCauseErrorDDEByRequirement(@PathVariable("id") Integer requirementId) {
        return typeErrorCharacteristicService.countTypeErrorEIEAndCauseErrorDDEByRequirement(requirementId);
    }

    @GetMapping(path = "/eie/dii/{id}")
    public int countTypeErrorEIEAndCauseErrorDIIByRequirement(@PathVariable("id") Integer requirementId) {
        return typeErrorCharacteristicService.countTypeErrorEIEAndCauseErrorDIIByRequirement(requirementId);
    }

    @GetMapping(path = "/eie/var/{id}")
    public int countTypeErrorEIEAndCauseErrorVARByRequirement(@PathVariable("id") Integer requirementId) {
        return typeErrorCharacteristicService.countTypeErrorEIEAndCauseErrorVARByRequirement(requirementId);
    }

    @GetMapping(path = "/mcc/{id}")
    public int countTypeErrorMCCByRequirement(@PathVariable("id") Integer requirementId) {
        return typeErrorCharacteristicService.countTypeErrorMCCByRequirement(requirementId);
    }

    @GetMapping(path = "/mcc/dde/{id}")
    public int countTypeErrorMCCAndCauseErrorDDEByRequirement(@PathVariable("id") Integer requirementId) {
        return typeErrorCharacteristicService.countTypeErrorMCCAndCauseErrorDDEByRequirement(requirementId);
    }

    @GetMapping(path = "/mcc/dii/{id}")
    public int countTypeErrorMCCAndCauseErrorDIIByRequirement(@PathVariable("id") Integer requirementId) {
        return typeErrorCharacteristicService.countTypeErrorMCCAndCauseErrorDIIByRequirement(requirementId);
    }

    @GetMapping(path = "/mcc/var/{id}")
    public int countTypeErrorMCCAndCauseErrorVARByRequirement(@PathVariable("id") Integer requirementId) {
        return typeErrorCharacteristicService.countTypeErrorMCCAndCauseErrorVARByRequirement(requirementId);
    }

    @GetMapping(path = "/all-number-percentage-operations/{id}")
    public List<Double> allNumberAntPercentageOperations(@PathVariable("id") Integer requirementId) {
        return typeErrorCharacteristicService.allNumbersAndPercentageOperations(requirementId);
    }

    @GetMapping(path = "/count-type-error-requirement/{typeErrorId}/{id}")
    public int countTypeErrorsByRequirements(
            @PathVariable("typeErrorId") Integer typeErrorId,
            @PathVariable("id") Integer requirementId
    ) {
        return typeErrorCharacteristicService.countTypeErrorsByRequirements(typeErrorId, requirementId);
    }

    @GetMapping(path = "/all-type-errors/{id}")
    public int countAllTypeErrorsByRequirement(@PathVariable("id") Integer requirementId) {
        return typeErrorCharacteristicService.countAllTypeErrorsByRequirement(requirementId);
    }

    @GetMapping(path = "/all-cause-errors/{id}")
    public int countCauseErrorsByRequirement(@PathVariable("id") Integer requirementId) {
        return typeErrorCharacteristicService.countAllCauseErrorsByRequirement(requirementId);
    }

    @GetMapping(path = "/percentage-of-type-error-eie-by-id/{id}")
    public double percentageOfTypeErrorEIEById(@PathVariable("id") Integer requirementId) {
        return typeErrorCharacteristicService.percentageOfTypeErrorEIEById(requirementId);
    }

    @GetMapping(path = "/percentage-of-type-error-mcc-by-id/{id}")
    public double percentageOfTypeErrorMCCById(@PathVariable("id") Integer requirementId) {
        return typeErrorCharacteristicService.percentageOfTypeErrorMCCById(requirementId);
    }

    @GetMapping(path = "/percentage-of-type-errors/{typeErrorID}/{id}")
    public double percentageOfTypeErrorsById(
            @PathVariable("id") Integer typeErrorID,
            @PathVariable("id") Integer requirementId
    ) {
        return typeErrorCharacteristicService.percentageOfTypeErrosById(typeErrorID, requirementId);
    }

    @GetMapping(path = "/percentage-of-all-type-error-by-id/{id}")
    public double percentageOfAllTypeErrorById(@PathVariable("id") Integer requirementId) {
        return typeErrorCharacteristicService.percentageOffAllTypeErrorById(requirementId);
    }

    @GetMapping(path = "/number-of-cause-error-by-id/{id}/{errorid}/{causeError}")
    public Integer numberOfCauseErrorById(
            @PathVariable("id") Integer requirementId,
            @PathVariable("errorid") Integer typeError,
            @PathVariable("causeError") String causeError
    ) {
        return typeErrorCharacteristicService.numberOfCauseErrorById(requirementId, typeError, causeError);
    }

    @GetMapping(path = "/percentage-of-cause-error-by-id/{id}/{errorid}/{causeError}")
    public double percentageOfCauseErrorById(
            @PathVariable("id") Integer requirementId,
            @PathVariable("errorid") Integer typeErrorId,
            @PathVariable("causeError") String causeError
    ) {
        return typeErrorCharacteristicService.percentageOfCauseErrorById(requirementId, typeErrorId, causeError);
    }

    @GetMapping(path = "/count-cause-error-by-characteristic-for-requirements")
    public IRequirementsByTypeAndCauseError causeErrorByCharacteristicForRequirements(
            @RequestParam(required = false) String typeRequirement,
            @RequestParam Integer projectId
    ) {
        return typeErrorCharacteristicService.causeErrorByCharacteristicForRequirements(typeRequirement, projectId);
    }

    @GetMapping(path = "/percentage-cause-error-by-characteristic-for-requirements")
    public Map<String, Double> percentageCauseErrorByCharacteristicForRequirements(
            @RequestParam(required = false) String typeRequirement,
            @RequestParam Integer projectId
    ) {
        IRequirementsByTypeAndCauseError requirements = causeErrorByCharacteristicForRequirements(typeRequirement, projectId);
        return typeErrorCharacteristicService.getPercentageCauseErrorByCharacteristicInterface(requirements);
    }

    @GetMapping(path = "/count-error-distribution-all-requirements")
    public IErrorDistributionAllRequirements countErrorDistributionAllRequirements(
            @RequestParam(required = false) String typeRequirement,
            @RequestParam Integer projectId
    ) {
        return typeErrorCharacteristicService.errorDistributionAllRequirements(typeRequirement, projectId);
    }

    @GetMapping(path = "/percentage-error-distribution-all-requirements")
    public Map<String, Double> percentageErrorDistributionRequirements(
            @RequestParam(required = false) String typeRequirement,
            @RequestParam Integer projectId
    ) {
        IErrorDistributionAllRequirements requirements = countErrorDistributionAllRequirements(typeRequirement, projectId);
        return typeErrorCharacteristicService.getPercentageErrorDistributionRequirementsInterface(requirements);
    }

}
