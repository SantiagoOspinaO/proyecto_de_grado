package co.com.crud.requirement.web.controller;

import co.com.crud.requirement.domain.model.Character;
import co.com.crud.requirement.domain.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/characters")
public class CharacterController {

    private final CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping()
    public ResponseEntity<List<Character>> getAllCharacters() {
        return ResponseEntity.ok(characterService.getAllCharacters());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Character>> getCharacterById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(characterService.getCharacterById(id));
    }
}
