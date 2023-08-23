package co.com.crud.requirement.domain.model;

import lombok.Getter;

@Getter
public class Character {

    private Integer characterId;

    private String name;

    private String description;

    private double grade;

    public void setCharacterId(Integer characterId) {
        this.characterId = characterId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }
}
