package co.com.crud.requirement.persistence.crud;

import co.com.crud.requirement.domain.model.queryresult.*;
import co.com.crud.requirement.persistence.entity.CharacteristicEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ICharacteristicCrudRepository extends CrudRepository<CharacteristicEntity, Integer> {

    @NotNull
    Optional<CharacteristicEntity> findById(@NotNull Integer id);

    @Query(value = "SELECT ncr.nota_caracteristica as grade " +
            "FROM requisito req " +
            "INNER JOIN nota_caracteristica_requisito ncr ON req.id = ncr.requisito_id " +
            "WHERE ncr.requisito_id = :requisitoId", nativeQuery = true)
    List<IGradeCharacteristicByRequirementId> findGradesByRequirementId(@Param("requisitoId") Integer requirementId);

    @Query(value = "SELECT " +
            "   c.id AS idCharacteristic, " +
            "   c.nombre AS nameCharacteristic, " +
            "   c.descripcion AS descriptionCharacteristic, " +
            "   c.nombre_opuesto AS oppositeName, " +
            "   c.descripcion_opuesta AS oppositeDescription, " +
            "   ncr.nota_caracteristica AS gradeCharacteristic, " +
            "   te.nombre AS typeError, " +
            "   te.descripcion AS descriptiontypeError, " +
            "   tec.dde AS dde, " +
            "   tec.dii AS dii, " +
            "   tec.var AS var " +
            "FROM requisito r " +
            "LEFT JOIN nota_caracteristica_requisito ncr ON r.id = ncr.requisito_id " +
            "LEFT JOIN caracteristica c ON ncr.caracteristica_id = c.id " +
            "LEFT JOIN tipo_error_caracteristica tec ON r.id = tec.requisito_id AND c.id = tec.caracteristica_id " +
            "LEFT JOIN tipo_error te ON tec.tipo_error_id = te.id " +
            "WHERE r.id = :requisitoId", nativeQuery = true)
    List<ICharacteristicsByRequirementId> findCharacteristicsByRequirementId(@Param("requisitoId") Integer requirementId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE nota_caracteristica_requisito " +
            "SET nota_caracteristica = :notaIngresada " +
            "WHERE requisito_id = :requisitoId " +
            "AND caracteristica_id = :caracteristicaId ", nativeQuery = true)
    void updateGradeCharacteristicByRequirement(
            @Param("notaIngresada") Double gradeInput,
            @Param("requisitoId") Integer requirementId,
            @Param("caracteristicaId") Integer characteristicId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE tipo_error_caracteristica " +
            "SET dde = :dde, dii = :dii, var = :var " +
            "WHERE requisito_id = :requisitoId " +
            "AND caracteristica_id = :caracteristicaId ", nativeQuery = true)
    void updateCauseErrorOfCharacteristic(
            @Param("dde") boolean dde,
            @Param("dii") boolean dii,
            @Param("var") boolean CE_VAR,
            @Param("requisitoId") Integer requirementId,
            @Param("caracteristicaId") Integer characteristicId);

    @Query(value = "SELECT " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre = 'Correcto' AND ncr.nota_caracteristica > 8), 0) AS Correcto, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'Incorrecto' AND ncr.nota_caracteristica <= 8), 0) AS Incorrecto, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre = 'Inequívoco' AND ncr.nota_caracteristica > 8), 0) AS Inequivoco, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'Ambiguo' AND ncr.nota_caracteristica <= 8), 0) AS Ambiguo, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre = 'Completo' AND ncr.nota_caracteristica > 8), 0) AS Completo, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'Incompleto' AND ncr.nota_caracteristica <= 8), 0) AS Incompleto, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre = 'Consistente' AND ncr.nota_caracteristica > 8), 0) AS Consistente, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'Débil' AND ncr.nota_caracteristica <= 8), 0) AS Debil, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre = 'Importante' AND ncr.nota_caracteristica > 8), 0) AS Importante, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'Intrascendente' AND ncr.nota_caracteristica <= 8), 0) AS Intrascendente, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre = 'Estable' AND ncr.nota_caracteristica > 8), 0) AS Estable, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'Inestable' AND ncr.nota_caracteristica <= 8), 0) AS Inestable, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre = 'Comprobable' AND ncr.nota_caracteristica > 8), 0) AS Comprobable, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'No Comprobable' AND ncr.nota_caracteristica <= 8), 0) AS NoComprobable, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre = 'Identificable' AND ncr.nota_caracteristica > 8), 0) AS Identificable, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'No Identificable' AND ncr.nota_caracteristica <= 8), 0) AS NoIdentificable, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre = 'Trazable' AND ncr.nota_caracteristica > 8), 0) AS Trazable, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'No Trazable' AND ncr.nota_caracteristica <= 8), 0) AS NoTrazable " +
            "FROM requisito r " +
            "INNER JOIN nota_caracteristica_requisito ncr ON r.id = ncr.requisito_id " +
            "INNER JOIN caracteristica c ON c.id = ncr.caracteristica_id " +
            "WHERE (:tipoRequisito = '' OR r.tipo_requisito = :tipoRequisito) " +
            "AND (r.proyecto_id = :proyectoId)", nativeQuery = true)
    IRequirementsByTypeAndNameCharacteristic countRequirementsByTypeAndNameCharacteristic(
            @Param("tipoRequisito") String typeRequirement,
            @Param("proyectoId") Integer projectId);

    @Query(value = "SELECT " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dde = true AND c.nombre_opuesto = 'Incorrecto'), 0) AS IncorrectoDDE, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dii = true AND c.nombre_opuesto = 'Incorrecto'), 0) AS IncorrectoDII, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.var = true AND c.nombre_opuesto = 'Incorrecto'), 0) AS IncorrectoVAR, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dde = true AND c.nombre_opuesto = 'Ambiguo'), 0) AS AmbiguoDDE, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dii = true AND c.nombre_opuesto = 'Ambiguo'), 0) AS AmbiguoDII, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.var = true AND c.nombre_opuesto = 'Ambiguo'), 0) AS AmbiguoVAR, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dde = true AND c.nombre_opuesto = 'Incompleto'), 0) AS IncompletoDDE, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dii = true AND c.nombre_opuesto = 'Incompleto'), 0) AS IncompletoDII, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.var = true AND c.nombre_opuesto = 'Incompleto'), 0) AS IncompletoVAR, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dde = true AND c.nombre_opuesto = 'Débil'), 0) AS DebilDDE, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dii = true AND c.nombre_opuesto = 'Débil'), 0) AS DebilDII, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.var = true AND c.nombre_opuesto = 'Débil'), 0) AS DebilVAR, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dde = true AND c.nombre_opuesto = 'Intrascendente'), 0) AS IntrascendenteDDE, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dii = true AND c.nombre_opuesto = 'Intrascendente'), 0) AS IntrascendenteDII, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.var = true AND c.nombre_opuesto = 'Intrascendente'), 0) AS IntrascendenteVAR, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dde = true AND c.nombre_opuesto = 'Inestable'), 0) AS InestableDDE, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dii = true AND c.nombre_opuesto = 'Inestable'), 0) AS InestableDII, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.var = true AND c.nombre_opuesto = 'Inestable'), 0) AS InestableVAR, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dde = true AND c.nombre_opuesto = 'No Comprobable'), 0) AS NoComprobableDDE, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dii = true AND c.nombre_opuesto = 'No Comprobable'), 0) AS NoComprobableDII, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.var = true AND c.nombre_opuesto = 'No Comprobable'), 0) AS NoComprobableVAR, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dde = true AND c.nombre_opuesto = 'No Identificable'), 0) AS NoIdentificableDDE, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dii = true AND c.nombre_opuesto = 'No Identificable'), 0) AS NoIdentificableDII, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.var = true AND c.nombre_opuesto = 'No Identificable'), 0) AS NoIdentificableVAR, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dde = true AND c.nombre_opuesto = 'No Trazable'), 0) AS NoTrazableDDE, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dii = true AND c.nombre_opuesto = 'No Trazable'), 0) AS NoTrazableDII, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.var = true AND c.nombre_opuesto = 'No Trazable'), 0) AS NoTrazableVAR " +
            "FROM tipo_error_caracteristica tec " +
            "INNER JOIN caracteristica c ON c.id = tec.caracteristica_id " +
            "INNER JOIN requisito r ON r.id = tec.requisito_id " +
            "WHERE (:tipoRequisito = '' OR r.tipo_requisito = :tipoRequisito) " +
            "AND (r.proyecto_id = :proyectoId) ", nativeQuery = true)
    IRequirementsByTypeAndCauseError countRequirementsByTypeAndCauseError(
            @Param("tipoRequisito") String typeRequirement,
            @Param("proyectoId") Integer projectId);

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
            "WHERE (:tipoRequisito = '' OR r.tipo_requisito = :tipoRequisito) " +
            "AND (r.proyecto_id = :proyectoId) ", nativeQuery = true)
    IRequirementsByFilterCauseError countCauseErrorByRequirementType(
            @Param("tipoRequisito") String typeRequirement,
            @Param("proyectoId") Integer projectId);

    @Query(value = "SELECT " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dde = true), 0) AS causeErrorDDE, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.dii = true), 0) AS causeErrorDII, " +
            "COALESCE(SUM(1) FILTER (WHERE tec.var = true), 0) AS causeErrorVAR " +
            "FROM tipo_error_caracteristica tec " +
            "JOIN caracteristica c ON c.id = tec.caracteristica_id " +
            "JOIN requisito r ON r.id = tec.requisito_id " +
            "WHERE r.id = :requisitoId " +
            "AND r.proyecto_id = :proyectoId ", nativeQuery = true)
    IRequirementsByRequirementIdAndCauseError countRequirementsByRequirementIdAndCauseError(
            @Param("requisitoId") Integer requirementId,
            @Param("proyectoId") Integer projectId);

    @Query(value = "SELECT " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'Incorrecto' AND ncr.nota_caracteristica <= 8 AND tec.dde = true), 0) AS incorrecto, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'Ambiguo' AND ncr.nota_caracteristica <= 8 AND tec.dde = true), 0) AS ambiguo, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'Débil' AND ncr.nota_caracteristica <= 8 AND tec.dde = true), 0) AS debil, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'Incompleto' AND ncr.nota_caracteristica <= 8 AND tec.dde = true), 0) AS incompleto, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'Intrascendente' AND ncr.nota_caracteristica <= 8 AND tec.dde = true), 0) AS intrascendente, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'Inestable' AND ncr.nota_caracteristica <= 8 AND tec.dde = true), 0) AS inestable, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'No Comprobable' AND ncr.nota_caracteristica <= 8 AND tec.dde = true), 0) AS noComprobable, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'No Identificable' AND ncr.nota_caracteristica <= 8 AND tec.dde = true), 0) AS noIdentificable, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'No Trazable' AND ncr.nota_caracteristica <= 8 AND tec.dde = true), 0) AS noTrazable " +
            "FROM requisito r " +
            "INNER JOIN nota_caracteristica_requisito ncr ON r.id = ncr.requisito_id " +
            "INNER JOIN caracteristica c ON c.id = ncr.caracteristica_id " +
            "LEFT JOIN public.tipo_error_caracteristica tec ON r.id = tec.requisito_id and c.id =tec.caracteristica_id  " +
            "WHERE (:tipoRequisito = '' OR r.tipo_requisito = :tipoRequisito) " +
            "AND (r.proyecto_id = :proyectoId) ", nativeQuery = true)
    ICharacteristicsByCauseError countCharacteristicsByCauseErrorDDE(
            @Param("tipoRequisito") String typeRequirement,
            @Param("proyectoId") Integer projectId);

    @Query(value = "SELECT " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'Incorrecto' AND ncr.nota_caracteristica <= 8 AND tec.dii = true), 0) AS incorrecto, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'Ambiguo' AND ncr.nota_caracteristica <= 8 AND tec.dii = true), 0) AS ambiguo, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'Débil' AND ncr.nota_caracteristica <= 8 AND tec.dii = true), 0) AS debil, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'Incompleto' AND ncr.nota_caracteristica <= 8 AND tec.dii = true), 0) AS incompleto, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'Intrascendente' AND ncr.nota_caracteristica <= 8 AND tec.dii = true), 0) AS intrascendente, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'Inestable' AND ncr.nota_caracteristica <= 8 AND tec.dii = true), 0) AS inestable, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'No Comprobable' AND ncr.nota_caracteristica <= 8 AND tec.dii = true), 0) AS noComprobable, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'No Identificable' AND ncr.nota_caracteristica <= 8 AND tec.dii = true), 0) AS noIdentificable, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'No Trazable' AND ncr.nota_caracteristica <= 8 AND tec.dii = true), 0) AS noTrazable " +
            "FROM requisito r " +
            "INNER JOIN nota_caracteristica_requisito ncr ON r.id = ncr.requisito_id " +
            "INNER JOIN caracteristica c ON c.id = ncr.caracteristica_id " +
            "LEFT JOIN public.tipo_error_caracteristica tec ON r.id = tec.requisito_id and c.id =tec.caracteristica_id  " +
            "WHERE (:tipoRequisito = '' OR r.tipo_requisito = :tipoRequisito) " +
            "AND (r.proyecto_id = :proyectoId) ", nativeQuery = true)
    ICharacteristicsByCauseError countCharacteristicsByCauseErrorDII(
            @Param("tipoRequisito") String typeRequirement,
            @Param("proyectoId") Integer projectId);

    @Query(value = "SELECT " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'Incorrecto' AND ncr.nota_caracteristica <= 8 AND tec.var = true), 0) AS incorrecto, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'Ambiguo' AND ncr.nota_caracteristica <= 8 AND tec.var = true), 0) AS ambiguo, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'Débil' AND ncr.nota_caracteristica <= 8 AND tec.var = true), 0) AS debil, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'Incompleto' AND ncr.nota_caracteristica <= 8 AND tec.var = true), 0) AS incompleto, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'Intrascendente' AND ncr.nota_caracteristica <= 8 AND tec.var = true), 0) AS intrascendente, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'Inestable' AND ncr.nota_caracteristica <= 8 AND tec.var = true), 0) AS inestable, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'No Comprobable' AND ncr.nota_caracteristica <= 8 AND tec.var = true), 0) AS noComprobable, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'No Identificable' AND ncr.nota_caracteristica <= 8 AND tec.var = true), 0) AS noIdentificable, " +
            "COALESCE(SUM(1) FILTER (WHERE c.nombre_opuesto = 'No Trazable' AND ncr.nota_caracteristica <= 8 AND tec.var = true), 0) AS noTrazable " +
            "FROM requisito r " +
            "INNER JOIN nota_caracteristica_requisito ncr ON r.id = ncr.requisito_id " +
            "INNER JOIN caracteristica c ON c.id = ncr.caracteristica_id " +
            "LEFT JOIN public.tipo_error_caracteristica tec ON r.id = tec.requisito_id and c.id =tec.caracteristica_id  " +
            "WHERE (:tipoRequisito = '' OR r.tipo_requisito = :tipoRequisito) " +
            "AND (r.proyecto_id = :proyectoId) ", nativeQuery = true)
    ICharacteristicsByCauseError countCharacteristicsByCauseErrorVAR(
            @Param("tipoRequisito") String typeRequirement,
            @Param("proyectoId") Integer projectId);

}