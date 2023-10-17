package co.com.crud.requirement.persistence.mapper;

import co.com.crud.requirement.domain.model.TypeErrorCharacteristic;
import co.com.crud.requirement.persistence.entity.TypeErrorCharacteristicEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TypeErrorCharacteristicMapper {
    @Mappings({
            @Mapping(source = "id", target = "typeErrorId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "descripcion", target = "description")
    })
    TypeErrorCharacteristic toTypeError(TypeErrorCharacteristicEntity typeErrorCharacteristicEntity);

    List<TypeErrorCharacteristic> toTypesErrorsEntities(List<TypeErrorCharacteristicEntity> typesErrors);

    @InheritInverseConfiguration
    TypeErrorCharacteristicEntity toTypesErrors(TypeErrorCharacteristic typeErrorCharacteristic);
}
