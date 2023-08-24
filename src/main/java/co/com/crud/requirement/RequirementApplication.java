package co.com.crud.requirement;

import co.com.crud.requirement.operations.leveladequacy.LevelAdequacy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RequirementApplication {

    public static void main(String[] args) {
        SpringApplication.run(RequirementApplication.class, args);

        LevelAdequacy levelAdequacy = new LevelAdequacy();
        System.out.println("Average: " + levelAdequacy.calculateLevelAdequacy());
    }
}