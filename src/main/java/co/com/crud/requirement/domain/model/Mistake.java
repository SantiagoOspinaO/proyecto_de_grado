package co.com.crud.requirement.domain.model;

public class Mistake {

    private Integer mistakeId;

    private String name;

    private String description;

    public Integer getMistakeId() {
        return mistakeId;
    }

    public void setMistakeId(Integer mistakeId) {
        this.mistakeId = mistakeId;
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
