package co.com.crud.requirement.persistence.mapper;

import co.com.crud.requirement.domain.model.CauseError;
import co.com.crud.requirement.persistence.entity.CauseErrorEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CauseErrorMapper {

    @Mappings({
            @Mapping(source = "id", target = "causeErrorId"),
            @Mapping(source = "nombre", target = "name"),
            @Mapping(source = "descripcion", target = "description"),
    })
    CauseError toCauseError(CauseErrorEntity CauseErrorEntity);

    List<CauseError> toCauseErrorsEntity(List<CauseErrorEntity> causeErrors);

    @InheritInverseConfiguration
    CauseErrorEntity toCauseErrors(CauseError causeError);
}
