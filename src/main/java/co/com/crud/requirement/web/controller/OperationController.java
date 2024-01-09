package co.com.crud.requirement.web.controller;

import co.com.crud.requirement.domain.model.Operation;
import co.com.crud.requirement.domain.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Operation> updateOperation(@RequestBody Operation operation,@PathVariable("id") Integer id) {
        operation.setOperationId(id);
        operation.setRequirementId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(this.operationService.saveOperation(operation));
    }

    @GetMapping()
    public ResponseEntity<List<Operation>> getAllOperations(){
        return ResponseEntity.ok(operationService.getAllOperations());
    }
}