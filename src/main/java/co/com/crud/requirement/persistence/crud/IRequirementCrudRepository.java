package co.com.crud.requirement.persistence.crud;

import co.com.crud.requirement.domain.model.queryresult.IPerfectOrNotPerfectRequirement;
import co.com.crud.requirement.domain.model.queryresult.IRequirementByGradeAndCauseError;
import co.com.crud.requirement.domain.model.queryresult.IRequirementsByFilterCauseError;
import co.com.crud.requirement.persistence.entity.RequirementEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
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
    IRequirementsByFilterCauseError countRequirementsByFilterCauseError(@Param("requisitoId") Integer requirementId);

    @Query(value = "SELECT " +
            "SUM(CASE WHEN ncr.nota_caracteristica > 8 AND tec.dii = true THEN 1 ELSE 0 END) AS PerfectEvaluationDII, " +
            "SUM(CASE WHEN ncr.nota_caracteristica > 0 AND ncr.nota_caracteristica <= 8 AND tec.dii = true THEN 1 ELSE 0 END) AS ImperfectEvaluationDII, " +
            "SUM(CASE WHEN ncr.nota_caracteristica > 8 AND tec.dde = true THEN 1 ELSE 0 END) AS PerfectEvaluationDDE, " +
            "SUM(CASE WHEN ncr.nota_caracteristica > 0 AND ncr.nota_caracteristica <= 8 AND tec.dde = true THEN 1 ELSE 0 END) AS ImperfectEvaluationDDE," +
            "SUM(CASE WHEN ncr.nota_caracteristica > 8 AND tec.var = true THEN 1 ELSE 0 END) AS PerfectEvaluationVAR, " +
            "SUM(CASE WHEN ncr.nota_caracteristica > 0 AND ncr.nota_caracteristica <= 8 AND tec.var = true THEN 1 ELSE 0 END) AS ImperfectEvaluationVAR " +
            "FROM requisito r " +
            "INNER JOIN nota_caracteristica_requisito ncr on r.id = ncr.requisito_id " +
            "INNER JOIN tipo_error_caracteristica tec ON r.id = tec.requisito_id " +
            "WHERE (r.tipo_requisito = :tipoRequisito) " +
            "AND " +
            "(" +
            "   (:causaError = 'dii' AND tec.dii = true) OR " +
            "   (:causaError = 'dde' AND tec.dde = true) OR " +
            "   (:causaError = 'var' AND tec.var = true)" +
            ") " +
            "AND (r.proyecto_id = :proyectoId) ", nativeQuery = true)
    IRequirementByGradeAndCauseError countRequirementsByGradeAndCauseError(
            @Param("tipoRequisito") String typeRequirement,
            @Param("causaError") String causeError,
            @Param("proyectoId") Integer projectId);

    @Query(value = "SELECT " +
            "SUM(1) FILTER (WHERE op.puntaje_maximo < 72) AS Imperfecto, " +
            "SUM(1) FILTER (WHERE op.puntaje_maximo > 72) AS Perfecto " +
            "FROM operacion op " +
            "INNER JOIN requisito r ON op.requisito_id=r.id " +
            "WHERE (r.proyecto_id = :proyectoId) " +
            "OR ((:tipoRequisito = '' OR r.tipo_requisito = :tipoRequisito) AND r.proyecto_id = :proyectoId )", nativeQuery = true)
    IPerfectOrNotPerfectRequirement countPerfectRequirements(
            @Param("tipoRequisito") String typeRequirement,
            @Param("proyectoId") Integer projectId);

    List<RequirementEntity> getRequirementsByProyectoId(Integer proyectoId);
}