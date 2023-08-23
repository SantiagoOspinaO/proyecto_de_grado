package co.com.crud.requirement.domain.model;

import lombok.Getter;

@Getter
public class TypeErrorCharacter {

    private Integer typeErrorId;

    private String name;

    private String description;

    private String causeError;

    public void setTypeErrorId(Integer typeErrorId) {
        this.typeErrorId = typeErrorId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCauseError(String causeError) {
        this.causeError = causeError;
    }
}
