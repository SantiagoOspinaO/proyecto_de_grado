package co.com.crud.requirement.web.controller;

import co.com.crud.requirement.domain.model.Mistake;
import co.com.crud.requirement.domain.service.MistakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/mistakes")
public class MistakeController {

    private final MistakeService mistakeService;
    @Autowired
    public MistakeController(MistakeService mistakeService) {
        this.mistakeService = mistakeService;
    }

    @GetMapping()
    public ResponseEntity<List<Mistake>> getAllMistakes() {
        return ResponseEntity.ok(mistakeService.getAllMistakes());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Mistake>> getMistakeById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(mistakeService.getMistakeById(id));
    }
}
