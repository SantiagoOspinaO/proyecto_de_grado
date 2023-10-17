package co.com.crud.requirement.web.controller;

import co.com.crud.requirement.domain.model.Characteristic;
import co.com.crud.requirement.domain.model.queryresult.ICharacteristicsByRequirementId;
import co.com.crud.requirement.domain.service.CharacteristicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/characteristics")
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

    @GetMapping(path = "/level-adequacy/{id}")
    public double calculateLevelAdequacy(@PathVariable("id") Integer requirementId) {
        return characteristicService.calculateLevelAdequacy(requirementId);
    }

    @PostMapping(path = "/update-grade/{requirementId}/{characteristicId}")
    public ResponseEntity<String> updateGradeCharacteristicByRequirement(@RequestBody Double gradeInput, @PathVariable Integer requirementId, @PathVariable Integer characteristicId) {
        characteristicService.updateGradeCharacteristicByRequirement(gradeInput, requirementId, characteristicId);
        return ResponseEntity.ok("¡Nota actualizada con exito!");
    }

    @PostMapping(path = "/update-type-error/{requirementId}/{characteristicId}/{typeErrorId}")
    public ResponseEntity<String> updateTypeErrorOfCharacteristic(
            @PathVariable Integer requirementId,
            @PathVariable Integer characteristicId,
            @PathVariable Integer typeErrorId,
            @RequestBody Map<String, Boolean> typeErrorData) {

        boolean dde = typeErrorData.get("dde");
        boolean dii = typeErrorData.get("dii");
        boolean var = typeErrorData.get("var");
        characteristicService.updateTypeErrorOfCharacteristic(dde, dii, var, requirementId, characteristicId, typeErrorId);
        return ResponseEntity.ok("¡Tipo de error actualizado con éxito!");
    }

    /*@GetMapping(path = "/maximun-accumulated-score")
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
    }*/

    @GetMapping(path = "characteristics-requirement/{id}")
    public List<ICharacteristicsByRequirementId> getCharacteristicByRequirement(@PathVariable("id") Integer requirementId) {
        return characteristicService.getCharacteristicByRequirement(requirementId);
    }
}
