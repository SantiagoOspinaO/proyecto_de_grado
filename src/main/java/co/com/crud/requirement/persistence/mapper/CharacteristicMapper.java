package co.com.crud.requirement.persistence.mapper;

import co.com.crud.requirement.domain.model.Characteristic;
import co.com.crud.requirement.persistence.entity.CharacteristicEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CharacteristicMapper {
    @Mappings({
            @Mapping(source = "id", target = "characteristicId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "nombreOpuesto", target = "oppositeName"),
            @Mapping(source = "descripcion", target = "description"),
            @Mapping(source = "descripcionOpuesta", target = "oppositeDescription")
    })
    Characteristic toCharacteristic(CharacteristicEntity characteristicEntity);

    List<Characteristic> toCharacteristicsEntity(List<CharacteristicEntity> characters);

    @InheritInverseConfiguration
    CharacteristicEntity toCharacteristics(Characteristic characteristic);

}
