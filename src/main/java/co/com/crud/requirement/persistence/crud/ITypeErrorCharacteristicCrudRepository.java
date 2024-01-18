package co.com.crud.requirement.persistence.crud;

import co.com.crud.requirement.domain.model.queryresult.IErrorDistributionAllRequirements;
import co.com.crud.requirement.domain.model.queryresult.IRequirementsByTypeAndCauseError;
import co.com.crud.requirement.persistence.entity.TypeErrorCharacteristicEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ITypeErrorCharacteristicCrudRepository extends CrudRepository<TypeErrorCharacteristicEntity, Integer> {

    @NotNull
    Optional<TypeErrorCharacteristicEntity> findById(@NotNull Integer id);

    @Query(value = "SELECT COUNT(r.id) " +
            "FROM requisito r " +
            "INNER JOIN tipo_error_caracteristica tec ON r.id = tec.requisito_id " +
            "INNER JOIN caracteristica c ON c.id = tec.caracteristica_id " +
            "WHERE (:tipoRequisito = '' OR r.tipo_requisito = :tipoRequisito) " +
            "AND " +
            "(" +
            "   (:causaError = 'dii' AND tec.dii = true) OR " +
            "   (:causaError = 'dde' AND tec.dde = true) OR " +
            "   (:causaError = 'var' AND tec.var = true)" +
            ")", nativeQuery = true)
    int countRequirementsByTypeAndCauseError(
            @Param("tipoRequisito") String typeRequirement,
            @Param("causaError") String causeError);

    @Query(value = "SELECT COUNT(*)  " +
            "FROM tipo_error_caracteristica " +
            "WHERE (requisito_id = :requisitoId AND tipo_error_id =:tipoErrorId AND dde = 'true' AND (:causaError = 'dde')) OR " +
            "(requisito_id = :requisitoId AND tipo_error_id =:tipoErrorId AND dii = 'true' AND (:causaError = 'dii')) OR" +
            "(requisito_id = :requisitoId AND tipo_error_id =:tipoErrorId AND var = 'true' AND (:causaError = 'var'))", nativeQuery = true)
    int countRequirementsByCauseErrorAndRequirementId(
            @Param("requisitoId") Integer requirementId,
            @Param("tipoErrorId") Integer typeErrorId,
            @Param("causaError") String causeError);

    @Query(value = "SELECT count(*) " +
            "FROM tipo_error_caracteristica " +
            "WHERE dde = 'true' ", nativeQuery = true)
    int countRequirementsByCauseErrorDDE();

    @Query(value = "SELECT count(*) " +
            "FROM tipo_error_caracteristica " +
            "WHERE dii = 'true' ", nativeQuery = true)
    int countRequirementsByCauseErrorDII();

    @Query(value = "SELECT count(*) " +
            "FROM tipo_error_caracteristica " +
            "WHERE var = 'true' ", nativeQuery = true)
    int countRequirementsByCauseErrorVAR();

    @Query(value = "SELECT count(*) " +
            "FROM nota_caracteristica_requisito ncr " +
            "JOIN tipo_error_caracteristica tec on ncr.caracteristica_id=tec.caracteristica_id and ncr.requisito_id=tec.requisito_id " +
            "WHERE tec.tipo_error_id = 1 and ncr.nota_caracteristica < 8 and ncr.requisito_id = :requisitoId", nativeQuery = true)
    int countTypeErrorEIEByRequirement(@Param("requisitoId") Integer requirementId);

    @Query(value = "SELECT count(*) " +
            "FROM tipo_error_caracteristica " +
            "WHERE tipo_error_id = 1 and requisito_id = :requisitoId and dde = 'true' ", nativeQuery = true)
    int countTypeErrorEIEAndCauseErrorDDEByRequirement(@Param("requisitoId") Integer requirementId);

    @Query(value = "SELECT count(*) " +
            "FROM tipo_error_caracteristica " +
            "WHERE tipo_error_id = 1 and requisito_id = :requisitoId and dii = 'true' ", nativeQuery = true)
    int countTypeErrorEIEAndCauseErrorDIIByRequirement(@Param("requisitoId") Integer requirementId);

    @Query(value = "SELECT count(*) " +
            "FROM tipo_error_caracteristica " +
            "WHERE tipo_error_id = 1 and requisito_id = :requisitoId and var = 'true' ", nativeQuery = true)
    int countTypeErrorEIEAndCauseErrorVARByRequirement(@Param("requisitoId") Integer requirementId);

    @Query(value = "SELECT count(*) " +
            "FROM nota_caracteristica_requisito ncr " +
            "JOIN tipo_error_caracteristica tec on ncr.caracteristica_id=tec.caracteristica_id and ncr.requisito_id=tec.requisito_id " +
            "WHERE tec.tipo_error_id = 2 and ncr.nota_caracteristica < 8 and ncr.requisito_id = :requisitoId", nativeQuery = true)
    int countTypeErrorMCCByRequirement(@Param("requisitoId") Integer requirementId);

    @Query(value = "SELECT count(*) " +
            "FROM tipo_error_caracteristica " +
            "WHERE tipo_error_id = 2 and requisito_id = :requisitoId and dde = 'true' ", nativeQuery = true)
    int countTypeErrorMCCAndCauseErrorDDEByRequirement(@Param("requisitoId") Integer requirementId);

    @Query(value = "SELECT count(*) " +
            "FROM tipo_error_caracteristica " +
            "WHERE tipo_error_id = 2 and requisito_id = :requisitoId and dii = 'true' ", nativeQuery = true)
    int countTypeErrorMCCAndCauseErrorDIIByRequirement(@Param("requisitoId") Integer requirementId);

    @Query(value = "SELECT count(*) " +
            "FROM tipo_error_caracteristica " +
            "WHERE tipo_error_id = 2 and requisito_id = :requisitoId and var = 'true' ", nativeQuery = true)
    int countTypeErrorMCCAndCauseErrorVARByRequirement(@Param("requisitoId") Integer requirementId);

    @Query(value = "SELECT count(*) " +
            "FROM tipo_error_caracteristica " +
            "WHERE (tipo_error_id = :typeErrorId) AND (requisito_id = :requisitoId)", nativeQuery = true)
    int countTypeErrorsByRequirements(@Param("typeErrorId") Integer typeErrorId,
                                      @Param("requisitoId") Integer requirementId);

    @Query(value = "SELECT count(*) " +
            "FROM tipo_error_caracteristica " +
            "WHERE requisito_id = :requisitoId", nativeQuery = true)
    int countAllTypeErrorsByRequirement(@Param("requisitoId") Integer requirementId);

    @Query(value = "SELECT count(*) " +
            "FROM tipo_error_caracteristica " +
            "WHERE (dde = 'true' OR  dii = 'true' OR var = 'true') AND tipo_error_id = :requisitoId", nativeQuery = true)
    int countAllCauseErrorsByRequirement(@Param("requisitoId") Integer requirementId);

    @Query(value = "SELECT " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dde = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'Incorrecto'), 0) AS IncorrectoDDE, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dii = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'Incorrecto'), 0) AS IncorrectoDII, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.var = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'Incorrecto'), 0) AS IncorrectoVAR, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dde = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'Ambiguo'), 0) AS AmbiguoDDE, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dii = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'Ambiguo'), 0) AS AmbiguoDII, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.var = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'Ambiguo'), 0) AS AmbiguoVAR, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dde = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'Incompleto'), 0) AS IncompletoDDE, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dii = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'Incompleto'), 0) AS IncompletoDII, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.var = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'Incompleto'), 0) AS IncompletoVAR, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dde = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'Débil'), 0) AS DebilDDE, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dii = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'Débil'), 0) AS DebilDII, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.var = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'Débil'), 0) AS DebilVAR, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dde = true AND tec.tipo_error_id = 2 AND c.nombre_opuesto = 'Intrascendente'), 0) AS IntrascendenteDDE, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dii = true AND tec.tipo_error_id = 2 AND c.nombre_opuesto = 'Intrascendente'), 0) AS IntrascendenteDII, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.var = true AND tec.tipo_error_id = 2 AND c.nombre_opuesto = 'Intrascendente'), 0) AS IntrascendenteVAR, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dde = true AND tec.tipo_error_id = 2 AND c.nombre_opuesto = 'Inestable'), 0) AS InestableDDE, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dii = true AND tec.tipo_error_id = 2 AND c.nombre_opuesto = 'Inestable'), 0) AS InestableDII, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.var = true AND tec.tipo_error_id = 2 AND c.nombre_opuesto = 'Inestable'), 0) AS InestableVAR, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dde = true AND tec.tipo_error_id = 2 AND c.nombre_opuesto = 'No Comprobable'), 0) AS NoComprobableDDE, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dii = true AND tec.tipo_error_id = 2 AND c.nombre_opuesto = 'No Comprobable'), 0) AS NoComprobableDII, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.var = true AND tec.tipo_error_id = 2 AND c.nombre_opuesto = 'No Comprobable'), 0) AS NoComprobableVAR, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dde = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'No Identificable'), 0) AS NoIdentificableDDE, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dii = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'No Identificable'), 0) AS NoIdentificableDII, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.var = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'No Identificable'), 0) AS NoIdentificableVAR, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dde = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'No Trazable'), 0) AS NoTrazableDDE, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dii = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'No Trazable'), 0) AS NoTrazableDII, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.var = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'No Trazable'), 0) AS NoTrazableVAR " +
            "FROM tipo_error_caracteristica tec " +
            "INNER JOIN requisito r ON tec.requisito_id = r.id " +
            "INNER JOIN caracteristica c ON tec.caracteristica_id = c.id " +
            "WHERE (r.proyecto_id = :proyectoId) " +
            "OR ((:tipoRequisito = '' OR r.tipo_requisito = :tipoRequisito) " +
            "AND r.proyecto_id = :proyectoId) ", nativeQuery = true)
    IRequirementsByTypeAndCauseError causeErrorByCharacteristicForRequirements(
            @Param("tipoRequisito") String typeRequirement,
            @Param("proyectoId") Integer projectId);

    @Query(value = "SELECT " +
            "COALESCE(SUM(1) FILTER (WHERE tec.tipo_error_id = 1 AND c.nombre_opuesto = 'Incorrecto'), 0) AS IncorrectoEIE, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.tipo_error_id = 1 AND c.nombre_opuesto = 'Ambiguo'), 0) AS AmbiguoEIE, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.tipo_error_id = 1 AND c.nombre_opuesto = 'Incompleto'), 0) AS IncompletoEIE, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.tipo_error_id = 1 AND c.nombre_opuesto = 'Débil'), 0) AS DebilEIE, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.tipo_error_id = 2 AND c.nombre_opuesto = 'Intrascendente'), 0) AS IntrascendenteMCC, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.tipo_error_id = 2 AND c.nombre_opuesto = 'Inestable'), 0) AS InestableMCC, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.tipo_error_id = 2 AND c.nombre_opuesto = 'No Comprobable'), 0) AS NoComprobableMCC, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.tipo_error_id = 1 AND c.nombre_opuesto = 'No Identificable'), 0) AS NoIdentificableEIE, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.tipo_error_id = 1 AND c.nombre_opuesto = 'No Trazable'), 0) AS NoTrazableEIE " +
            "FROM tipo_error_caracteristica tec " +
            "INNER JOIN requisito r ON tec.requisito_id = r.id " +
            "INNER JOIN caracteristica c ON tec.caracteristica_id = c.id " +
            "INNER JOIN nota_caracteristica_requisito ncr ON ncr.caracteristica_id = tec.caracteristica_id " +
            "AND ncr.requisito_id = tec.requisito_id " +
            "WHERE (ncr.nota_caracteristica < 8) " +
            "AND (:tipoRequisito = '' OR r.tipo_requisito = :tipoRequisito) " +
            "AND (r.proyecto_id = :proyectoId) ", nativeQuery = true)
    IErrorDistributionAllRequirements errorDistributionAllRequirements(
            @Param("tipoRequisito") String typeRequirement,
            @Param("proyectoId") Integer projectId);

}