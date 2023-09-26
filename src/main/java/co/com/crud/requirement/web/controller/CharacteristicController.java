package co.com.crud.requirement.web.controller;

import co.com.crud.requirement.domain.model.Characteristic;
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

//    public ResponseEntity<Optional<Characteristic>> getCharacteristicByRequirement() {
//        try {
//            Connection connection = ConnectionToDB.getConnectionToDB();
//            Statement statement = connection.createStatement();
//            String query = "SELECT * FROM consultar_notas_caracteristica();";
//            ResultSet resultSet = statement.executeQuery(query);
//        } catch (SQLException e) {
//            System.out.println("ERROR: " + e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
