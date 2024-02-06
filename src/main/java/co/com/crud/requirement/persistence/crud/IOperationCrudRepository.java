package co.com.crud.requirement.persistence.crud;

import co.com.crud.requirement.domain.model.queryresult.ITotalMaxScore;
import co.com.crud.requirement.persistence.entity.OperationEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IOperationCrudRepository extends CrudRepository<OperationEntity, Integer> {

    @Query(value = "SELECT " +
            "COALESCE(SUM(1) FILTER (WHERE op.puntaje_maximo <= 81 AND op.puntaje_maximo > 72.09), 0) AS AltoAlto, " +
            "COALESCE(SUM(1) FILTER (WHERE op.puntaje_maximo <= 72.09 AND op.puntaje_maximo > 63.09), 0) AS AltoMedio, " +
            "COALESCE(SUM(1) FILTER (WHERE op.puntaje_maximo <= 63.09 AND op.puntaje_maximo > 54.09), 0) AS AltoBajo, " +
            "COALESCE(SUM(1) FILTER (WHERE op.puntaje_maximo <= 54.09 AND op.puntaje_maximo > 45.09), 0) AS MedioAlto, " +
            "COALESCE(SUM(1) FILTER (WHERE op.puntaje_maximo <= 45.09 AND op.puntaje_maximo > 36.09), 0) AS MedioMedio, " +
            "COALESCE(SUM(1) FILTER (WHERE op.puntaje_maximo <= 36.09 AND op.puntaje_maximo > 27.09), 0) AS MedioBajo, " +
            "COALESCE(SUM(1) FILTER (WHERE op.puntaje_maximo <= 27.09 AND op.puntaje_maximo > 18.09), 0) AS BajoAlto, " +
            "COALESCE(SUM(1) FILTER (WHERE op.puntaje_maximo <= 18.09 AND op.puntaje_maximo > 9.09), 0) AS BajoMedio, " +
            "COALESCE(SUM(1) FILTER (WHERE op.puntaje_maximo <= 9.09 ), 0) AS BajoBajo " +
            "FROM operacion op " +
            "INNER JOIN requisito r ON r.id = op.requisito_id " +
            "WHERE (:tipoRequisito = '' OR r.tipo_requisito = :tipoRequisito) " +
            "AND (r.proyecto_id = :proyectoId) ", nativeQuery = true)
    ITotalMaxScore countNumberScoreByProjectIdOrTypeRequirement(
            @Param("tipoRequisito") String typeRequirement,
            @Param("proyectoId") Integer projectId);

    @Query(value = "SELECT " +
            "COALESCE(SUM(op.puntaje_maximo) FILTER (WHERE op.puntaje_maximo <= 81 AND op.puntaje_maximo > 72.09), 0) AS AltoAlto, " +
            "COALESCE(SUM(op.puntaje_maximo) FILTER (WHERE op.puntaje_maximo <= 72.09 AND op.puntaje_maximo > 63.09), 0) AS AltoMedio, " +
            "COALESCE(SUM(op.puntaje_maximo) FILTER (WHERE op.puntaje_maximo <= 63.09 AND op.puntaje_maximo > 54.09), 0) AS AltoBajo, " +
            "COALESCE(SUM(op.puntaje_maximo) FILTER (WHERE op.puntaje_maximo <= 54.09 AND op.puntaje_maximo > 45.09), 0) AS MedioAlto, " +
            "COALESCE(SUM(op.puntaje_maximo) FILTER (WHERE op.puntaje_maximo <= 45.09 AND op.puntaje_maximo > 36.09), 0) AS MedioMedio, " +
            "COALESCE(SUM(op.puntaje_maximo) FILTER (WHERE op.puntaje_maximo <= 36.09 AND op.puntaje_maximo > 27.09), 0) AS MedioBajo, " +
            "COALESCE(SUM(op.puntaje_maximo) FILTER (WHERE op.puntaje_maximo <= 27.09 AND op.puntaje_maximo > 18.09), 0) AS BajoAlto, " +
            "COALESCE(SUM(op.puntaje_maximo) FILTER (WHERE op.puntaje_maximo <= 18.09 AND op.puntaje_maximo > 9.09), 0) AS BajoMedio, " +
            "COALESCE(SUM(op.puntaje_maximo) FILTER (WHERE op.puntaje_maximo <= 9.09 ), 0) AS BajoBajo " +
            "FROM operacion op " +
            "INNER JOIN requisito r ON r.id = op.requisito_id " +
            "WHERE (:tipoRequisito = '' OR r.tipo_requisito = :tipoRequisito) " +
            "AND (r.proyecto_id = :proyectoId) ", nativeQuery = true)
    ITotalMaxScore countAllScoreByProjectIdOrTypeRequirement(
            @Param("tipoRequisito") String typeRequirement,
            @Param("proyectoId") Integer projectId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE operacion " +
            "SET puntaje_maximo = :puntaje_maximo, " +
            "nivel_adecuacion = :nivel_adecuacion, " +
            "caracteristicas_evaluadas = :caracteristicas_evaluadas, " +
            "nivel_caracteristica_puntuacion = :nivel_caracteristica_puntuacion, " +
            "promedio_calculado = :promedio_calculado " +
            "FROM requisito " +
            "WHERE operacion.requisito_id = requisito.id " +
            "AND operacion.requisito_id = :requisitoId ", nativeQuery = true)
    void updateOperation(
            @Param("puntaje_maximo") Double puntaje_maximo,
            @Param("nivel_adecuacion") Double nivel_adecuacion,
            @Param("caracteristicas_evaluadas") Double caracteristicas_evaluadas,
            @Param("nivel_caracteristica_puntuacion") Double nivel_caracteristica_puntuacion,
            @Param("promedio_calculado") Double promedio_calculado,
            @Param("requisitoId") Integer requisitoId);

}
