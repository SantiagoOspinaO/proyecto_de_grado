package co.com.crud.requirement.web.controller;

import co.com.crud.requirement.domain.model.Requirement;
import co.com.crud.requirement.domain.model.queryresult.IPerfectOrNotPerfectRequirement;
import co.com.crud.requirement.domain.service.RequirementService;
import co.com.crud.requirement.domain.model.queryresult.IRequirementByGradeAndCauseError;
import co.com.crud.requirement.domain.model.queryresult.IRequirementsByFilterCauseError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/requirements")
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

    @DeleteMapping(path = "{id}")
    public ResponseEntity deleteRequirement(@PathVariable("id") Integer requirementId) {
        if (requirementService.deleteRequirement(requirementId)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/count-requirement-id-filter-cause-error")
    public IRequirementsByFilterCauseError countRequirementsByFilterCauseError(@RequestParam Integer requirementId) {
        return requirementService.countRequirementsByFilterCauseError(requirementId);
    }

    @GetMapping(path = "/count-requirement-grade-cause-error")
    public IRequirementByGradeAndCauseError countRequirementsByGradeAndCauseError(
            @RequestParam String typeRequirement,
            @RequestParam String causeError,
            @RequestParam Integer projectId
    ) {
        return requirementService.countRequirementsByGradeAndCauseError(typeRequirement, causeError, projectId);
    }

    @GetMapping(path = "/count-perfect-requirements/{id}")
    public IPerfectOrNotPerfectRequirement countPerfectRequirements(@PathVariable("id") Integer projectId){
        return requirementService.countPerfectRequirements(projectId);
    }

    @GetMapping(path = "/count-perfect-requirements1")
    public IPerfectOrNotPerfectRequirement countPerfectRequirements1(
            @RequestParam String typeRequirement,
            @RequestParam Integer projectId
    ){
        return requirementService.countPerfectRequirements1(typeRequirement,projectId);
    }

}