package co.com.crud.requirement.persistence.mapper;

import co.com.crud.requirement.domain.model.Requirement;
import co.com.crud.requirement.persistence.entity.RequirementEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RequirementMapper {

    @Mapping(source = "id", target = "requirementId")
    @Mapping(source = "proyectoId", target = "projectId")
    @Mapping(source = "nombre", target = "name")
    @Mapping(source = "descripcion", target = "description")
    @Mapping(source = "tipoRequisito", target = "typeRequirement")
    @Mapping(source = "calificado", target = "qualified")
    Requirement toRequirement(RequirementEntity requirementEntity);

    List<Requirement> toRequirementsEntity(List<RequirementEntity> requirements);

    @InheritInverseConfiguration
    RequirementEntity toRequirements(Requirement requirement);

}