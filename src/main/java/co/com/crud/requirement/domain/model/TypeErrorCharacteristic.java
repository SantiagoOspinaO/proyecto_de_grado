package co.com.crud.requirement.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypeErrorCharacteristic {

    private Integer typeErrorId;

    private String name;

    private String description;

    private String causeError;
}