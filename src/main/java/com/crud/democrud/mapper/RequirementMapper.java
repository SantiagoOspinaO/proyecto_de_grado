package com.crud.democrud.mapper;

import com.crud.democrud.dto.RequirementDTO;
import com.crud.democrud.models.RequirementModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RequirementMapper {

    @Mapping(source = "id", target = "requisitoId")
    @Mapping(source = "nombre", target = "nombre")
    @Mapping(source = "descripcion", target = "descripcion")
    @Mapping(source = "tipoRequisito", target = "tipoRequisito")
    RequirementDTO requirementDto(RequirementModel requirementModel);
    List<RequirementDTO> requirementDtos(List<RequirementModel> requirementModels);

}
