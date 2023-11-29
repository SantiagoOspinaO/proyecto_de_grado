package co.com.crud.requirement.persistence.crud;

import co.com.crud.requirement.persistence.entity.RequirementEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IRequirementCrudRepository extends CrudRepository<RequirementEntity, Integer> {

    Optional<RequirementEntity> findById(Integer id);

//    @Query(value = "SELECT COUNT(r.id) " +
//            "FROM requisito r " +
//            "INNER JOIN caracteristica c ON r.id = c.id " +
//            "WHERE r.tipo_requisito = :tipoRequisito " +
//            "AND c.nombre = :nombreCaracteristica")
//    int countRequirementsByCharacteristic(@Param("tipoRequisito") String typeRequirement,
//                                          @Param("nombreCaracteristica") String nameCharacteristic);
}