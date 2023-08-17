package co.com.crud.requirement.web.controller;

import co.com.crud.requirement.domain.model.CauseError;
import co.com.crud.requirement.domain.service.CauseErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/causeErrors")
public class CauseErrorController {

    private final CauseErrorService causeErrorService;
    @Autowired
    public CauseErrorController(CauseErrorService causeErrorService) {
        this.causeErrorService = causeErrorService;
    }

    @GetMapping()
    public ResponseEntity<List<CauseError>> getAllCauseErrors() {
        return ResponseEntity.ok(causeErrorService.getAllCauseErrors());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<CauseError>> getCauseErrorById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(causeErrorService.getCauseErrorsById(id));
    }
}
