package co.com.crud.requirement.persistence.mapper;

import co.com.crud.requirement.domain.model.TypeErrorCharacter;
import co.com.crud.requirement.persistence.entity.TypeErrorCharacterEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TypeErrorCharacterMapper {
    @Mappings({
            @Mapping(source = "id", target = "typeErrorId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "descripcion", target = "description"),
            @Mapping(source = "causaError", target = "causeError")
    })
    TypeErrorCharacter toTypeError(TypeErrorCharacterEntity typeErrorCharacterEntity);

    List<TypeErrorCharacter> toTypesErrorsEntities(List<TypeErrorCharacterEntity> typesErrors);

    @InheritInverseConfiguration
    TypeErrorCharacterEntity toTypesErrors(TypeErrorCharacter typeErrorCharacter);
}
