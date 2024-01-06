package co.com.crud.requirement.persistence.crud;

import co.com.crud.requirement.domain.model.queryresult.*;
import co.com.crud.requirement.persistence.entity.CharacteristicEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ICharacteristicCrudRepository extends CrudRepository<CharacteristicEntity, Integer> {

    Optional<CharacteristicEntity> findById(Integer id);

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
    @Query(value = "UPDATE caracteristica c " +
            "JOIN nota_caracteristica_requisito ncr ON c.id = ncr.caracteristica_id " +
            "JOIN requisito r ON ncr.requisito_id = r.id " +
            "LEFT JOIN tipo_error_caracteristica tec ON r.id = tec.requisito_id AND c.id = tec.caracteristica_id " +
            "SET c.nombre = :nombre, " +
            "    c.descripcion = :descripcion, " +
            "    c.nombre_opuesto = :nombre_opuesto, " +
            "    c.descripcion_opuesta = :descripcion_opuesta, " +
            "    ncr.nota_caracteristica = :nota_caracteristica, " +
            "    tec.dde = :dde, " +
            "    tec.dii = :dii, " +
            "    tec.var = :var " +
            "WHERE r.id = :requisitoId AND c.id = :caracteristicaId", nativeQuery = true)
    void updateCharacteristicByRequirementId(
            @Param("requisitoId") Integer requirementId,
            @Param("caracteristicaId") Integer characteristicId,
            @Param("nombre") String name,
            @Param("descripcion") String description,
            @Param("nombre_opuesto") String oppositeName,
            @Param("descripcion_opuesta") String oppositeDescription,
            @Param("nota_caracteristica") Double gradeCharacteristic,
            @Param("dde") boolean dde,
            @Param("dii") boolean dii,
            @Param("var") boolean var);

    @Modifying
    @Transactional
    @Query(value = "UPDATE nota_caracteristica_requisito " +
            "SET nota_caracteristica = :notaIngresada " +
            "WHERE requisito_id = :requisitoId AND caracteristica_id = :caracteristicaId", nativeQuery = true)
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
            @Param("var") boolean var,
            @Param("requisitoId") Integer requirementId,
            @Param("caracteristicaId") Integer characteristicId);

    @Query(value = "SELECT " +
            "SUM(1) FILTER (WHERE c.nombre = 'Correcto' AND ncr.nota_caracteristica > 8) AS Correcto, " +
            "SUM(1) FILTER (WHERE c.nombre_opuesto = 'Incorrecto' AND ncr.nota_caracteristica <= 8) AS Incorrecto, " +
            "SUM(1) FILTER (WHERE c.nombre = 'Inequívoco' AND ncr.nota_caracteristica > 8) AS Inequivoco, " +
            "SUM(1) FILTER (WHERE c.nombre_opuesto = 'Ambiguo' AND ncr.nota_caracteristica <= 8) AS Ambiguo, " +
            "SUM(1) FILTER (WHERE c.nombre = 'Completo' AND ncr.nota_caracteristica > 8) AS Completo, " +
            "SUM(1) FILTER (WHERE c.nombre_opuesto = 'Incompleto' AND ncr.nota_caracteristica <= 8) AS Incompleto, " +
            "SUM(1) FILTER (WHERE c.nombre = 'Consistente' AND ncr.nota_caracteristica > 8) AS Consistente, " +
            "SUM(1) FILTER (WHERE c.nombre_opuesto = 'Débil' AND ncr.nota_caracteristica <= 8) AS Debil, " +
            "SUM(1) FILTER (WHERE c.nombre = 'Importante' AND ncr.nota_caracteristica > 8) AS Importante, " +
            "SUM(1) FILTER (WHERE c.nombre_opuesto = 'Intrascendente' AND ncr.nota_caracteristica <= 8) AS Intrascendente, " +
            "SUM(1) FILTER (WHERE c.nombre = 'Estable' AND ncr.nota_caracteristica > 8) AS Estable, " +
            "SUM(1) FILTER (WHERE c.nombre_opuesto = 'Inestable' AND ncr.nota_caracteristica <= 8) AS Inestable, " +
            "SUM(1) FILTER (WHERE c.nombre = 'Comprobable' AND ncr.nota_caracteristica > 8) AS Comprobable, " +
            "SUM(1) FILTER (WHERE c.nombre_opuesto = 'No Comprobable' AND ncr.nota_caracteristica <= 8) AS NoComprobable, " +
            "SUM(1) FILTER (WHERE c.nombre = 'Identificable' AND ncr.nota_caracteristica > 8) AS Identificable, " +
            "SUM(1) FILTER (WHERE c.nombre_opuesto = 'No Identificable' AND ncr.nota_caracteristica <= 8) AS NoIdentificable, " +
            "SUM(1) FILTER (WHERE c.nombre = 'Trazable' AND ncr.nota_caracteristica > 8) AS Trazable, " +
            "SUM(1) FILTER (WHERE c.nombre_opuesto = 'No Trazable' AND ncr.nota_caracteristica <= 8) AS NoTrazable " +
            "FROM requisito r " +
            "INNER JOIN nota_caracteristica_requisito ncr ON r.id = ncr.requisito_id " +
            "INNER JOIN caracteristica c ON c.id = ncr.caracteristica_id " +
            "WHERE (:tipoRequisito = '' OR r.tipo_requisito = :tipoRequisito) " +
            "AND (r.proyecto_id = :proyectoId)", nativeQuery = true)
    IRequirementsByTypeAndNameCharacteristic countRequirementsByTypeAndNameCharacteristic(
            @Param("tipoRequisito") String typeRequirement,
            @Param("proyectoId") Integer projectId);

    @Query(value = "SELECT " +
            "SUM(1) FILTER (WHERE tec.dde = true AND c.nombre_opuesto = 'Incorrecto' ) AS IncorrectoDDE, " +
            "SUM(1) FILTER (WHERE tec.dii = true AND c.nombre_opuesto = 'Incorrecto' ) AS IncorrectoDII, " +
            "SUM(1) FILTER (WHERE tec.var = true AND c.nombre_opuesto = 'Incorrecto') AS IncorrectoVAR, " +
            "SUM(1) FILTER (WHERE tec.dde = true AND c.nombre_opuesto = 'Ambiguo' ) AS AmbiguoDDE, " +
            "SUM(1) FILTER (WHERE tec.dii = true AND c.nombre_opuesto = 'Ambiguo' ) AS AmbiguoDII, " +
            "SUM(1) FILTER (WHERE tec.var = true AND c.nombre_opuesto = 'Ambiguo') AS AmbiguoVAR, " +
            "SUM(1) FILTER (WHERE tec.dde = true AND c.nombre_opuesto = 'Incompleto' ) AS IncompletoDDE, " +
            "SUM(1) FILTER (WHERE tec.dii = true AND c.nombre_opuesto = 'Incompleto' ) AS IncompletoDII, " +
            "SUM(1) FILTER (WHERE tec.var = true AND c.nombre_opuesto = 'Incompleto') AS IncompletoVAR, " +
            "SUM(1) FILTER (WHERE tec.dde = true AND c.nombre_opuesto = 'Débil' ) AS DebilDDE, " +
            "SUM(1) FILTER (WHERE tec.dii = true AND c.nombre_opuesto = 'Débil' ) AS DebilDII, " +
            "SUM(1) FILTER (WHERE tec.var = true AND c.nombre_opuesto = 'Débil') AS DebilVAR, " +
            "SUM(1) FILTER (WHERE tec.dde = true AND c.nombre_opuesto = 'Intrascendente' ) AS IntrascendenteDDE, " +
            "SUM(1) FILTER (WHERE tec.dii = true AND c.nombre_opuesto = 'Intrascendente' ) AS IntrascendenteDII, " +
            "SUM(1) FILTER (WHERE tec.var = true AND c.nombre_opuesto = 'Intrascendente') AS IntrascendenteVAR, " +
            "SUM(1) FILTER (WHERE tec.dde = true AND c.nombre_opuesto = 'Inestable' ) AS InestableDDE, " +
            "SUM(1) FILTER (WHERE tec.dii = true AND c.nombre_opuesto = 'Inestable' ) AS InestableDII, " +
            "SUM(1) FILTER (WHERE tec.var = true AND c.nombre_opuesto = 'Inestable') AS InestableVAR, " +
            "SUM(1) FILTER (WHERE tec.dde = true AND c.nombre_opuesto = 'No Comprobable' ) AS NoComprobableDDE, " +
            "SUM(1) FILTER (WHERE tec.dii = true AND c.nombre_opuesto = 'No Comprobable' ) AS NoComprobableDII, " +
            "SUM(1) FILTER (WHERE tec.var = true AND c.nombre_opuesto = 'No Comprobable') AS NoComprobableVAR, " +
            "SUM(1) FILTER (WHERE tec.dde = true AND c.nombre_opuesto = 'No Identificable' ) AS NoIdentificableDDE, " +
            "SUM(1) FILTER (WHERE tec.dii = true AND c.nombre_opuesto = 'No Identificable' ) AS NoIdentificableDII, " +
            "SUM(1) FILTER (WHERE tec.var = true AND c.nombre_opuesto = 'No Identificable') AS NoIdentificableVAR, " +
            "SUM(1) FILTER (WHERE tec.dde = true AND c.nombre_opuesto = 'No Trazable' ) AS NoTrazableDDE, " +
            "SUM(1) FILTER (WHERE tec.dii = true AND c.nombre_opuesto = 'No Trazable' ) AS NoTrazableDII, " +
            "SUM(1) FILTER (WHERE tec.var = true AND c.nombre_opuesto = 'No Trazable') AS NoTrazableVAR " +
            "FROM tipo_error_caracteristica tec " +
            "INNER JOIN caracteristica c ON c.id = tec.caracteristica_id " +
            "INNER JOIN requisito r ON r.id = tec.requisito_id " +
            "WHERE (r.tipo_requisito = :tipoRequisito) " +
            "AND (r.proyecto_id = :proyectoId) ", nativeQuery = true)
    IRequirementsByTypeAndCauseError countRequirementsByTypeAndCauseError(
            @Param("tipoRequisito") String typeRequirement,
            @Param("proyectoId") Integer projectId);

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
            "WHERE (:tipoRequisito = '' OR r.tipo_requisito = :tipoRequisito) " +
            "AND (r.proyecto_id = :proyectoId) ", nativeQuery = true)
    IRequirementsByFilterCauseError countCauseErrorByRequirementType(
            @Param("tipoRequisito") String typeRequirement,
            @Param("proyectoId") Integer projectId);

    @Query(value = "SELECT " +
            "SUM(1) FILTER (WHERE tec.dde = true) AS causeErrorDDE, " +
            "SUM(1) FILTER (WHERE tec.dii = true) AS causeErrorDII, " +
            "SUM(1) FILTER (WHERE tec.var = true) AS causeErrorVAR " +
            "FROM tipo_error_caracteristica tec " +
            "JOIN caracteristica c ON c.id = tec.caracteristica_id " +
            "JOIN requisito r ON r.id = tec.requisito_id " +
            "WHERE r.id = :requisitoId " +
            "AND r.proyecto_id = :proyectoId ", nativeQuery = true)
    IRequirementsByRequirementIdAndCauseError countRequirementsByRequirementIdAndCauseError(
            @Param("requisitoId") Integer requirementId,
            @Param("proyectoId") Integer projectId);

}