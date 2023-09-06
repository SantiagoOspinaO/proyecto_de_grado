package co.com.crud.requirement.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Characteristic {

    private Integer characteristicId;

    private String name;

    private String description;

    private double grade;

}