package co.com.crud.requirement.web.controller;

import co.com.crud.requirement.domain.model.AverageScore;
import co.com.crud.requirement.domain.model.Operation;
import co.com.crud.requirement.domain.model.queryresult.ITotalMaxScore;
import co.com.crud.requirement.domain.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/operations")
public class OperationController {

    private final OperationService operationService;

    @Autowired
    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Operation> updateOperation(@RequestBody Operation operation, @PathVariable("id") Integer id) {
        operation.setOperationId(id);
        operation.setRequirementId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.operationService.saveOperation(operation));
    }

    @GetMapping()
    public ResponseEntity<List<Operation>> getAllOperations() {
        return ResponseEntity.ok(operationService.getAllOperations());
    }

    @GetMapping(path = "/count-number-score-by-projectId-or-typeRequirement")
    public ITotalMaxScore countNumberScoreByProjectIdOrTypeRequirement(
            @RequestParam(required = false) String typeRequirement,
            @RequestParam Integer projectId
    ) {
        return operationService.countNumberScoreByProjectIdOrTypeRequirement(typeRequirement, projectId);
    }

    @GetMapping(path = "/count-all-score-by-projectId-or-typeRequirement")
    public ITotalMaxScore countAllScoreByProjectIdOrTypeRequirement(
            @RequestParam(required = false) String typeRequirement,
            @RequestParam Integer projectId) {
        return operationService.countAllScoreByProjectIdOrTypeRequirement(typeRequirement, projectId);
    }

    @GetMapping(path = "/averageScoreByProjectIdOrTypeRequirement")
    public AverageScore averageScoreByProjectIdOrTypeRequirement(
            @RequestParam(required = false) String typeRequirement,
            @RequestParam Integer projectId) {
        return operationService.averageScoreByProjectIdOrTypeRequirement(typeRequirement, projectId);
    }

    @GetMapping(path = "/prueba")
    public double prueba(@RequestParam Integer projectId) {

        AverageScore funcional = averageScoreByProjectIdOrTypeRequirement("funcional", projectId);
        AverageScore noFuncional = averageScoreByProjectIdOrTypeRequirement("no funcional", projectId);

        List<Double> numElementos = new ArrayList<>();
        numElementos.add(funcional.getAltoAlto());
        numElementos.add(noFuncional.getAltoAlto());

        Arrays.sort(numElementos.toArray());

        double mediana;
        int tamanio = numElementos.size();

        if (tamanio % 2 == 0) {
            double sumaMedios = numElementos.get(tamanio / 2) + numElementos.get((tamanio / 2) - 1);
            mediana = sumaMedios / 2;
        } else {
            mediana = numElementos.get(tamanio / 2);
        }

        return mediana;
    }

}
