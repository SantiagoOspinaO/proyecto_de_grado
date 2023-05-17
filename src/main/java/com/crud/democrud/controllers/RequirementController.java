package com.crud.democrud.controllers;

import com.crud.democrud.models.RequirementModel;
import com.crud.democrud.services.RequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<RequirementModel> saveRequirement(@RequestBody RequirementModel requirement) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.requirementService.saveRequirement(requirement));
    }

    @GetMapping()
    public ResponseEntity<List<RequirementModel>> getAllRequirements() {
        return ResponseEntity.ok(requirementService.getAllRequirements());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<List<RequirementModel>> getRequirementById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(requirementService.getRequirementById(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<RequirementModel> updateRequirement(@RequestBody RequirementModel requirement, @PathVariable("id") Integer id) {
        requirement.setId(id);
        return ResponseEntity.ok(requirementService.saveRequirement(requirement));
    }

}