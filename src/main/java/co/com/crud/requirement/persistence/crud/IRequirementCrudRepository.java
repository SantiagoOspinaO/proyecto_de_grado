package co.com.crud.requirement.persistence.crud;

import co.com.crud.requirement.persistence.entity.RequirementEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IRequirementCrudRepository extends CrudRepository<RequirementEntity, Integer> {

    Optional<RequirementEntity> findById(Integer id);

    @Query(value = "SELECT " +
            "SUM(1) FILTER (WHERE ncr.nota_caracteristica > 0 AND ncr.nota_caracteristica <= 3 AND c.id IN (5,6,7)) AS severeMCC, " +
            "SUM(1) FILTER (WHERE ncr.nota_caracteristica > 3 AND ncr.nota_caracteristica <= 6 AND c.id IN (5,6,7)) AS moderateMCC, " +
            "SUM(1) FILTER (WHERE ncr.nota_caracteristica > 6 AND ncr.nota_caracteristica <= 8 AND c.id IN (5,6,7)) AS mildMCC, " +
            "SUM(1) FILTER (WHERE ncr.nota_caracteristica > 0 AND ncr.nota_caracteristica <= 3 AND c.id IN (1,2,3,4,8,9)) AS severeEIE, " +
            "SUM(1) FILTER (WHERE ncr.nota_caracteristica > 3 AND ncr.nota_caracteristica <= 6 AND c.id IN (1,2,3,4,8,9)) AS moderateEIE, " +
            "SUM(1) FILTER (WHERE ncr.nota_caracteristica > 6 AND ncr.nota_caracteristica <= 8 AND c.id IN (1,2,3,4,8,9)) AS mildEIE " +
            "FROM requisito r " +
            "INNER JOIN nota_caracteristica_requisito ncr ON r.id = ncr.requisito_id " +
            "INNER JOIN caracteristica c ON c.id = ncr.caracteristica_id " +
            "WHERE r.id = :requisitoId", nativeQuery = true)
    IMCCAndEIEStatisticsDTO countRequirementsByFilterCauseError(@Param("requisitoId") Integer requirementId);
}