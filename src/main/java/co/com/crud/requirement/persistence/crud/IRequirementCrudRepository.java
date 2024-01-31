package co.com.crud.requirement.persistence.crud;

import co.com.crud.requirement.domain.model.queryresult.IPerfectOrNotPerfectRequirement;
import co.com.crud.requirement.domain.model.queryresult.IRequirementByGradeAndCauseError;
import co.com.crud.requirement.domain.model.queryresult.IRequirementsByFilterCauseError;
import co.com.crud.requirement.domain.model.queryresult.ITypeConsultingProject;
import co.com.crud.requirement.persistence.entity.RequirementEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface IRequirementCrudRepository extends CrudRepository<RequirementEntity, Integer> {

    @NotNull
    Optional<RequirementEntity> findById(@NotNull Integer id);

    @Query(value = "SELECT " +
            "COALESCE(SUM(1) FILTER (WHERE ncr.nota_caracteristica > 0 AND ncr.nota_caracteristica <= 3 AND c.id IN (5,6,7)), 0) AS severeMCC, " +
            "COALESCE(SUM(1) FILTER (WHERE ncr.nota_caracteristica > 3 AND ncr.nota_caracteristica <= 6 AND c.id IN (5,6,7)), 0) AS moderateMCC, " +
            "COALESCE(SUM(1) FILTER (WHERE ncr.nota_caracteristica > 6 AND ncr.nota_caracteristica <= 8 AND c.id IN (5,6,7)), 0) AS mildMCC, " +
            "COALESCE(SUM(1) FILTER (WHERE ncr.nota_caracteristica > 0 AND ncr.nota_caracteristica <= 3 AND c.id IN (1,2,3,4,8,9)), 0) AS severeEIE, " +
            "COALESCE(SUM(1) FILTER (WHERE ncr.nota_caracteristica > 3 AND ncr.nota_caracteristica <= 6 AND c.id IN (1,2,3,4,8,9)), 0) AS moderateEIE, " +
            "COALESCE(SUM(1) FILTER (WHERE ncr.nota_caracteristica > 6 AND ncr.nota_caracteristica <= 8 AND c.id IN (1,2,3,4,8,9)), 0) AS mildEIE " +
            "FROM requisito r " +
            "INNER JOIN nota_caracteristica_requisito ncr ON r.id = ncr.requisito_id " +
            "INNER JOIN caracteristica c ON c.id = ncr.caracteristica_id " +
            "WHERE (r.id = :requisitoId) " +
            "AND (r.proyecto_id = :proyectoId) ", nativeQuery = true)
    IRequirementsByFilterCauseError countRequirementsByFilterCauseError(
            @Param("requisitoId") Integer requirementId,
            @Param("proyectoId") Integer projectId);

    @Query(value = "SELECT " +
            "COALESCE(SUM(CASE WHEN ncr.nota_caracteristica > 8 AND tec.dii = true THEN 1 ELSE 0 END), 0) AS PerfectEvaluationDII, " +
            "COALESCE(SUM(CASE WHEN ncr.nota_caracteristica > 0 AND ncr.nota_caracteristica <= 8 AND tec.dii = true THEN 1 ELSE 0 END), 0) AS ImperfectEvaluationDII, " +
            "COALESCE(SUM(CASE WHEN ncr.nota_caracteristica > 8 AND tec.dde = true THEN 1 ELSE 0 END), 0) AS PerfectEvaluationDDE, " +
            "COALESCE(SUM(CASE WHEN ncr.nota_caracteristica > 0 AND ncr.nota_caracteristica <= 8 AND tec.dde = true THEN 1 ELSE 0 END), 0) AS ImperfectEvaluationDDE, " +
            "COALESCE(SUM(CASE WHEN ncr.nota_caracteristica > 8 AND tec.var = true THEN 1 ELSE 0 END), 0) AS PerfectEvaluationVAR, " +
            "COALESCE(SUM(CASE WHEN ncr.nota_caracteristica > 0 AND ncr.nota_caracteristica <= 8 AND tec.var = true THEN 1 ELSE 0 END), 0) AS ImperfectEvaluationVAR " +
            "FROM requisito r " +
            "INNER JOIN nota_caracteristica_requisito ncr ON r.id = ncr.requisito_id " +
            "INNER JOIN tipo_error_caracteristica tec ON r.id = tec.requisito_id " +
            "WHERE (r.tipo_requisito = :tipoRequisito) " +
            "AND " +
            "(" +
            "   (:causaError = 'dii' AND tec.dii = true) OR " +
            "   (:causaError = 'dde' AND tec.dde = true) OR " +
            "   (:causaError = 'var' AND tec.var = true)" +
            ") " +
            "AND (r.proyecto_id = :proyectoId)", nativeQuery = true)
    IRequirementByGradeAndCauseError countRequirementsByGradeAndCauseError(
            @Param("tipoRequisito") String typeRequirement,
            @Param("causaError") String causeError,
            @Param("proyectoId") Integer projectId);

    @Query(value = "SELECT " +
            "COALESCE(SUM(1) FILTER (WHERE op.puntaje_maximo <= 72.09), 0) AS Imperfecto, " +
            "COALESCE(SUM(1) FILTER (WHERE op.puntaje_maximo > 72.09), 0) AS Perfecto " +
            "FROM operacion op " +
            "INNER JOIN requisito r ON op.requisito_id=r.id " +
            "WHERE (r.proyecto_id = :proyectoId) " +
            "OR ((:tipoRequisito = '' OR r.tipo_requisito = :tipoRequisito)" +
            "AND r.proyecto_id = :proyectoId )", nativeQuery = true)
    IPerfectOrNotPerfectRequirement countPerfectRequirements(
            @Param("tipoRequisito") String typeRequirement,
            @Param("proyectoId") Integer projectId);

    List<RequirementEntity> getRequirementsByProyectoId(Integer proyectoId);

    @Query(value = "SELECT COUNT(r.id) " +
            "FROM requisito r " +
            "WHERE (:tipoRequisito = '' OR r.tipo_requisito = :tipoRequisito) " +
            "AND (r.proyecto_id = :proyectoId) ", nativeQuery = true)
    int countAllRequirements(
            @Param("tipoRequisito") String typeRequirement,
            @Param("proyectoId") Integer projectId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE requisito SET calificado = true WHERE id = :requisitoId", nativeQuery = true)
    void updateQualifiedByRequirementId(@Param("requisitoId") Integer requirementId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE estado_proyecto " +
            "SET estado = 7 " +
            "FROM proyecto " +
            "WHERE estado_proyecto.id = proyecto.estado " +
            "AND proyecto.id = :proyectoId ", nativeQuery = true)
    void updateProjectStatus(@Param("proyectoId") Integer proyectoId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM rol_seleccion " +
            "USING seleccion " +
            "WHERE seleccion.id = rol_seleccion.seleccion " +
            "AND proyecto = :proyectoId ", nativeQuery = true)
    void deleteDataRoleSelection(@Param("proyectoId") Integer proyectoId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM rol_usuario " +
            "USING seleccion " +
            "WHERE seleccion.usuario = rol_usuario.usuario " +
            "AND rol NOT IN (1, 2, 3) " +
            "AND proyecto = :proyectoId ", nativeQuery = true)
    void deleteDataRoleUser(@Param("proyectoId") Integer proyectoId);

    @Query(value = "SELECT " +
            "CASE WHEN COALESCE(SUM(1) FILTER (WHERE tipo_consultoria = 1), 0) = 1 THEN true ELSE false END AS requirementsEngineering, " +
            "CASE WHEN COALESCE(SUM(1) FILTER (WHERE tipo_consultoria = 2), 0) = 1 THEN true ELSE false END AS sqa, " +
            "CASE WHEN COALESCE(SUM(1) FILTER (WHERE tipo_consultoria = 3), 0) = 1 THEN true ELSE false END AS sqc " +
            "FROM tipo_consultoria_proyecto " +
            "WHERE proyecto = :proyectoId ", nativeQuery = true)
    ITypeConsultingProject getTypeOfConsulting(@Param("proyectoId") Integer proyectoId);

}