package co.com.crud.requirement.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Requirement {

    private Integer requirementId;

    private Integer projectId;

    private String name;

    private String description;

    private String typeRequirement;

    private boolean qualified;

}