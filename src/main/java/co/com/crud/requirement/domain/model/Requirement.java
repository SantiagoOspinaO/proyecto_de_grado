package co.com.crud.requirement.domain.model;

public class Requirement {

    private Integer requirementId;
    private String name;
    private String description;
    private String typeRequirement;


    public Integer getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(Integer requirementId) {
        this.requirementId = requirementId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeRequirement() {
        return typeRequirement;
    }

    public void setTypeRequirement(String typeRequirement) {
        this.typeRequirement = typeRequirement;
    }
}
