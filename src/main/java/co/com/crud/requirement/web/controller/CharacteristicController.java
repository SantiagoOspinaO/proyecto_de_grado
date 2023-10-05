package co.com.crud.requirement.web.controller;

import co.com.crud.requirement.domain.model.Characteristic;
import co.com.crud.requirement.domain.model.queryresult.ICharacteristicByRequirement;
import co.com.crud.requirement.domain.service.CharacteristicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/characters")
public class CharacteristicController {

    private final CharacteristicService characteristicService;

    @Autowired
    public CharacteristicController(CharacteristicService characteristicService) {
        this.characteristicService = characteristicService;
    }

    @GetMapping()
    public ResponseEntity<List<Characteristic>> getAllCharacteristics() {
        return ResponseEntity.ok(characteristicService.getAllCharacteristics());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Characteristic>> getCharacteristicById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(characteristicService.getCharacteristicById(id));
    }

    @GetMapping(path = "/level-adequacy")
    public double calculateLevelAdequacy() {
        return characteristicService.calculateLevelAdequacy();
    }

    @GetMapping(path = "/maximunAccumulatedScore")
    public double calculateMaximunAccumulatedScore() {
        return characteristicService.maximunAccumulatedScore();
    }

    @GetMapping(path = "/level-weight-score")
    public double calculateLevelWeightScoreForNineCharacters() {
        return characteristicService.levelWeightScoreForNineCharacters();
    }

    @GetMapping(path = "/all-evaluation-characters-result")
    public String calculateAllEvaluationCharactersResult() {
        return characteristicService.allEvaluationCharactersResult();
    }

    @GetMapping(path = "characteristics-requirement")
    public List<ICharacteristicByRequirement> getCharacteristicByRequirement() {
        return characteristicService.getCharacteristicByRequirement();
    }
}
