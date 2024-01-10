package co.com.crud.requirement.persistence.crud;

import co.com.crud.requirement.domain.model.queryresult.IErrorDistributionAllRequirements;
import co.com.crud.requirement.domain.model.queryresult.IPerfectOrNotPerfectRequirement;
import co.com.crud.requirement.domain.model.queryresult.IRequirementsByTypeAndCauseError;
import co.com.crud.requirement.persistence.entity.TypeErrorCharacteristicEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ITypeErrorCharacteristicCrudRepository extends CrudRepository<TypeErrorCharacteristicEntity, Integer> {

    Optional<TypeErrorCharacteristicEntity> findById(Integer id);

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
            "SUM(1) FILTER (WHERE tec.dde = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'Incorrecto' ) AS IncorrectoDDE, " +
            "SUM(1) FILTER (WHERE tec.dii = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'Incorrecto' ) AS IncorrectoDII, " +
            "SUM(1) FILTER (WHERE tec.var = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'Incorrecto' ) AS IncorrectoVAR, " +
            "SUM(1) FILTER (WHERE tec.dde = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'Ambiguo' ) AS AmbiguoDDE, " +
            "SUM(1) FILTER (WHERE tec.dii = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'Ambiguo' ) AS AmbiguoDII, " +
            "SUM(1) FILTER (WHERE tec.var = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'Ambiguo' ) AS AmbiguoVAR, " +
            "SUM(1) FILTER (WHERE tec.dde = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'Incompleto' ) AS IncompletoDDE, " +
            "SUM(1) FILTER (WHERE tec.dii = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'Incompleto' ) AS IncompletoDII, " +
            "SUM(1) FILTER (WHERE tec.var = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'Incompleto' ) AS IncompletoVAR, " +
            "SUM(1) FILTER (WHERE tec.dde = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'Débil' ) AS DebilDDE, " +
            "SUM(1) FILTER (WHERE tec.dii = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'Débil' ) AS DebilDII, " +
            "SUM(1) FILTER (WHERE tec.var = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'Débil' ) AS DebilVAR, " +
            "SUM(1) FILTER (WHERE tec.dde = true AND tec.tipo_error_id = 2 AND c.nombre_opuesto = 'Intrascendente' ) AS IntrascendenteDDE, " +
            "SUM(1) FILTER (WHERE tec.dii = true AND tec.tipo_error_id = 2 AND c.nombre_opuesto = 'Intrascendente' ) AS IntrascendenteDII, " +
            "SUM(1) FILTER (wHERE tec.var = true AND tec.tipo_error_id = 2 AND c.nombre_opuesto = 'Intrascendente' ) AS IntrascendenteVAR, " +
            "SUM(1) FILTER (WHERE tec.dde = true AND tec.tipo_error_id = 2 AND c.nombre_opuesto = 'Inestable' ) AS InestableDDE, " +
            "SUM(1) FILTER (WHERE tec.dii = true AND tec.tipo_error_id = 2 AND c.nombre_opuesto = 'Inestable' ) AS InestableDII, " +
            "SUM(1) FILTER (WHERE tec.var = true AND tec.tipo_error_id = 2 AND c.nombre_opuesto = 'Inestable' ) AS InestableVAR, " +
            "SUM(1) FILTER (WHERE tec.dde = true AND tec.tipo_error_id = 2 AND c.nombre_opuesto = 'No Comprobable' ) AS NoComprobableDDE, " +
            "SUM(1) FILTER (WHERE tec.dii = true AND tec.tipo_error_id = 2 AND c.nombre_opuesto = 'No Comprobable' ) AS NoComprobableDII, " +
            "SUM(1) FILTER (WHERE tec.var = true AND tec.tipo_error_id = 2 AND c.nombre_opuesto = 'No Comprobable' ) AS NoComprobableVAR, " +
            "SUM(1) FILTER (WHERE tec.dde = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'No Identificable' ) AS NoIdentificableDDE, " +
            "SUM(1) FILTER (WHERE tec.dii = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'No Identificable' ) AS NoIdentificableDII, " +
            "SUM(1) FILTER (WHERE tec.var = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'No Identificable' ) AS NoIdentificableVAR, " +
            "SUM(1) FILTER (WHERE tec.dde = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'No Trazable' ) AS NoTrazableDDE, " +
            "SUM(1) FILTER (WHERE tec.dii = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'No Trazable' ) AS NoTrazableDII, " +
            "SUM(1) FILTER (WHERE tec.var = true AND tec.tipo_error_id = 1 AND c.nombre_opuesto = 'No Trazable' ) AS NoTrazableVAR " +
            "FROM tipo_error_caracteristica tec " +
            "INNER JOIN requisito r on tec.requisito_id=r.id " +
            "INNER JOIN caracteristica c on tec.caracteristica_id=c.id " +
            "WHERE (r.proyecto_id = :proyectoId) " +
            "OR ((:tipoRequisito = '' OR r.tipo_requisito = :tipoRequisito) AND r.proyecto_id = :proyectoId ) ", nativeQuery = true)
    IRequirementsByTypeAndCauseError causeErrorByCharacteristicForRequirements(
            @Param("tipoRequisito") String typeRequirement,
            @Param("proyectoId") Integer projectId);

    @Query(value = "SELECT " +
            "SUM(1) FILTER (WHERE tec.tipo_error_id = 1 AND c.nombre_opuesto = 'Incorrecto' ) AS IncorrectoEIE, " +
            "SUM(1) FILTER (WHERE tec.tipo_error_id = 1 AND c.nombre_opuesto = 'Ambiguo' ) AS AmbiguoEIE, " +
            "SUM(1) FILTER (WHERE tec.tipo_error_id = 1 AND c.nombre_opuesto = 'Incompleto' ) AS IncompletoEIE, " +
            "SUM(1) FILTER (WHERE tec.tipo_error_id = 1 AND c.nombre_opuesto = 'Débil' ) AS DebilEIE, " +
            "SUM(1) FILTER (WHERE tec.tipo_error_id = 2 AND c.nombre_opuesto = 'Intrascendente' ) AS IntrascendenteMCC, " +
            "SUM(1) FILTER (WHERE tec.tipo_error_id = 2 AND c.nombre_opuesto = 'Inestable' ) AS InestableMCC, " +
            "SUM(1) FILTER (WHERE tec.tipo_error_id = 2 AND c.nombre_opuesto = 'No Comprobable') AS NoComprobableMCC, " +
            "SUM(1) FILTER (WHERE tec.tipo_error_id = 1 AND c.nombre_opuesto = 'No Identificable') AS NoIdentificableEIE, " +
            "SUM(1) FILTER (WHERE tec.tipo_error_id = 1 AND c.nombre_opuesto = 'No Trazable') AS NoTrazableEIE " +
            "FROM tipo_error_caracteristica tec " +
            "INNER JOIN requisito r on tec.requisito_id=r.id " +
            "INNER JOIN caracteristica c on tec.caracteristica_id=c.id " +
            "INNER JOIN nota_caracteristica_requisito ncr on ncr.caracteristica_id = tec.caracteristica_id and ncr.requisito_id = tec.requisito_id " +
            "WHERE ((r.proyecto_id = :proyectoId) AND ncr.nota_caracteristica < 8) " +
            "OR (((:tipoRequisito = '' OR r.tipo_requisito = :tipoRequisito) AND r.proyecto_id = :proyectoId) AND ncr.nota_caracteristica < 8) ", nativeQuery = true)
    IErrorDistributionAllRequirements errorDistributionAllRequirements(
            @Param("tipoRequisito") String typeRequirement,
            @Param("proyectoId") Integer projectId);

}