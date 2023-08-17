package co.com.crud.requirement.domain.model;

public class CauseError {

    public Integer causeErrorId;

    private String name;

    private String description;

    public Integer getCauseErrorId() {
        return causeErrorId;
    }

    public void setCauseErrorId(Integer causeErrorId) {
        this.causeErrorId = causeErrorId;
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
}
