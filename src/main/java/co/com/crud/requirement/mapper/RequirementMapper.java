package co.com.crud.requirement.mapper;

import co.com.crud.requirement.dto.RequirementDTO;
import co.com.crud.requirement.model.RequirementModel;
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
