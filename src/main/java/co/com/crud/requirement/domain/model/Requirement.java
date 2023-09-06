package co.com.crud.requirement.domain.model;

import lombok.Getter;

import java.util.List;

@Getter
public class Requirement {

    private Integer requirementId;
    private String name;
    private String description;
    private String typeRequirement;

    public void setRequirementId(Integer requirementId) {
        this.requirementId = requirementId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTypeRequirement(String typeRequirement) {
        this.typeRequirement = typeRequirement;
    }
}
