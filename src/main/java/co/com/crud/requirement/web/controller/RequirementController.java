package co.com.crud.requirement.web.controller;

import co.com.crud.requirement.domain.model.Requirement;
import co.com.crud.requirement.domain.service.RequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/requisitos")
public class RequirementController {

    private final RequirementService requirementService;

    @Autowired
    public RequirementController(RequirementService requirementService) {
        this.requirementService = requirementService;
    }

    @PostMapping()
    public ResponseEntity<Requirement> saveRequirement(@RequestBody Requirement requirement) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.requirementService.saveRequirement(requirement));
    }

    @GetMapping()
    public ResponseEntity<List<Requirement>> getAllRequirements() {
        return ResponseEntity.ok(requirementService.getAllRequirements());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Requirement>> getRequirementById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(requirementService.getRequirementById(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Requirement> updateRequirement(@RequestBody Requirement requirement, @PathVariable("id") Integer id) {
        requirement.setRequirementId(id);
        return ResponseEntity.ok(requirementService.saveRequirement(requirement));
    }
}