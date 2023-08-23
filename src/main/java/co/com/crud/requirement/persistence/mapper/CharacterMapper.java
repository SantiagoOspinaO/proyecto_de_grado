package co.com.crud.requirement.persistence.mapper;

import co.com.crud.requirement.domain.model.Character;
import co.com.crud.requirement.persistence.entity.CharacterEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CharacterMapper {
    @Mappings({
            @Mapping(source = "id", target = "characterId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "descripcion", target = "description"),
            @Mapping(source = "nota", target = "grade")
    })
    Character toCharacter(CharacterEntity characterEntity);

    List<Character> toCharactersEntity(List<CharacterEntity> characters);

    @InheritInverseConfiguration
    CharacterEntity toCharacters(Character character);
}
