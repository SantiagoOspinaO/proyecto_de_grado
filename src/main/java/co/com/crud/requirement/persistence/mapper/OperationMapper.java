package co.com.crud.requirement.persistence.mapper;

import co.com.crud.requirement.domain.model.Operation;
import co.com.crud.requirement.persistence.entity.OperationEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OperationMapper {

    @Mappings({
            @Mapping(source = "id", target = "operationId"),
            @Mapping(source = "requisitoId", target = "requirementId"),
            @Mapping(source = "nivelAdecuacion", target = "levelAdecuacy"),
            @Mapping(source = "caracteristicasEvaluadas", target = "evaluatedCharacteristics"),
            @Mapping(source = "nivelCaracteristicaPuntuacion", target = "levelWeightScore"),
            @Mapping(source = "puntajeMaximo", target = "maximumScore"),
            @Mapping(source = "promedioCalculado", target = "calculatedWeightAverage")
    })
    Operation toOperation(OperationEntity operationEntity);

    List<Operation> toOperationEntity(List<OperationEntity> operations);

    @InheritInverseConfiguration
    OperationEntity toOperations(Operation operation);
}
