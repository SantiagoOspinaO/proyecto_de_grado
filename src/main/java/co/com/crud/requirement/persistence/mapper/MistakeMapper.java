package co.com.crud.requirement.persistence.mapper;

import co.com.crud.requirement.domain.model.Mistake;
import co.com.crud.requirement.persistence.entity.MistakeEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MistakeMapper {
    @Mappings({
            @Mapping(source = "id", target = "mistakeId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "descripcion", target = "description"),
    })
    Mistake toMistake(MistakeEntity mistakeEntity);

    List<Mistake> toMistakesEntity(List<MistakeEntity> mistakes);

    @InheritInverseConfiguration
    MistakeEntity toMistakes(Mistake mistake);
}
