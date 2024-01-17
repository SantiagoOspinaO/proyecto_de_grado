package co.com.crud.requirement.persistence.crud;

import co.com.crud.requirement.domain.model.queryresult.ITotalMaxScore;
import co.com.crud.requirement.persistence.entity.OperationEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IOperationCrudRepository extends CrudRepository<OperationEntity, Integer> {

    @Query(value = "SELECT " +
            "COALESCE(SUM(1) FILTER (WHERE op.puntaje_maximo <= 81 AND op.puntaje_maximo > 72), 0) AS AltoAlto, " +
            "COALESCE(SUM(1) FILTER (WHERE op.puntaje_maximo <= 72 AND op.puntaje_maximo > 63), 0) AS AltoMedio, " +
            "COALESCE(SUM(1) FILTER (WHERE op.puntaje_maximo <= 63 AND op.puntaje_maximo > 54), 0) AS AltoBajo, " +
            "COALESCE(SUM(1) FILTER (WHERE op.puntaje_maximo <= 54 AND op.puntaje_maximo > 45), 0) AS MedioAlto, " +
            "COALESCE(SUM(1) FILTER (WHERE op.puntaje_maximo <= 45 AND op.puntaje_maximo > 36), 0) AS MedioMedio, " +
            "COALESCE(SUM(1) FILTER (WHERE op.puntaje_maximo <= 36 AND op.puntaje_maximo > 27), 0) AS MedioBajo, " +
            "COALESCE(SUM(1) FILTER (WHERE op.puntaje_maximo <= 27 AND op.puntaje_maximo > 18), 0) AS BajoAlto, " +
            "COALESCE(SUM(1) FILTER (WHERE op.puntaje_maximo <= 18 AND op.puntaje_maximo > 9), 0) AS BajoMedio, " +
            "COALESCE(SUM(1) FILTER (WHERE op.puntaje_maximo < 9 ), 0) AS BajoBajo " +
            "FROM operacion op " +
            "INNER JOIN requisito r ON r.id = op.requisito_id " +
            "WHERE (:tipoRequisito = '' OR r.tipo_requisito = :tipoRequisito) " +
            "AND (r.proyecto_id = :proyectoId) ", nativeQuery = true)
    ITotalMaxScore countNumberScoreByProjectIdOrTypeRequirement(
            @Param("tipoRequisito") String typeRequirement,
            @Param("proyectoId") Integer projectId);

    @Query(value = "SELECT " +
            "COALESCE(SUM(op.puntaje_maximo) FILTER (WHERE op.puntaje_maximo <= 81 AND op.puntaje_maximo > 72), 0) AS AltoAlto, " +
            "COALESCE(SUM(op.puntaje_maximo) FILTER (WHERE op.puntaje_maximo <= 72 AND op.puntaje_maximo > 63), 0) AS AltoMedio, " +
            "COALESCE(SUM(op.puntaje_maximo) FILTER (WHERE op.puntaje_maximo <= 63 AND op.puntaje_maximo > 54), 0) AS AltoBajo, " +
            "COALESCE(SUM(op.puntaje_maximo) FILTER (WHERE op.puntaje_maximo <= 54 AND op.puntaje_maximo > 45), 0) AS MedioAlto, " +
            "COALESCE(SUM(op.puntaje_maximo) FILTER (WHERE op.puntaje_maximo <= 45 AND op.puntaje_maximo > 36), 0) AS MedioMedio, " +
            "COALESCE(SUM(op.puntaje_maximo) FILTER (WHERE op.puntaje_maximo <= 36 AND op.puntaje_maximo > 27), 0) AS MedioBajo, " +
            "COALESCE(SUM(op.puntaje_maximo) FILTER (WHERE op.puntaje_maximo <= 27 AND op.puntaje_maximo > 18), 0) AS BajoAlto, " +
            "COALESCE(SUM(op.puntaje_maximo) FILTER (WHERE op.puntaje_maximo <= 18 AND op.puntaje_maximo > 9), 0) AS BajoMedio, " +
            "COALESCE(SUM(op.puntaje_maximo) FILTER (WHERE op.puntaje_maximo < 9 ), 0) AS BajoBajo " +
            "FROM operacion op " +
            "INNER JOIN requisito r ON r.id = op.requisito_id " +
            "WHERE (r.tipo_requisito = :tipoRequisito) " +
            "AND (r.proyecto_id = :proyectoId) ", nativeQuery = true)
    ITotalMaxScore countAllScoreByProjectIdOrTypeRequirement(
            @Param("tipoRequisito") String typeRequirement,
            @Param("proyectoId") Integer projectId);

}
