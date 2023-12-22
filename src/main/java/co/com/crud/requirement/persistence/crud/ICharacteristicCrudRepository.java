package co.com.crud.requirement.persistence.crud;

import co.com.crud.requirement.domain.model.queryresult.ICharacteristicsByRequirementId;
import co.com.crud.requirement.domain.model.queryresult.IGradeCharacteristicByRequirementId;
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
            "   tec.dde as dde, " +
            "   tec.dii as dii, " +
            "   tec.var as var " +
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
    void updateCharacteristicByRequirementId(@Param("requisitoId") Integer requirementId,
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
    void updateGradeCharacteristicByRequirement(@Param("notaIngresada") Double gradeInput,
                                                @Param("requisitoId") Integer requirementId,
                                                @Param("caracteristicaId") Integer characteristicId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE tipo_error_caracteristica " +
            "SET dde = :dde, dii = :dii, var = :var " +
            "WHERE requisito_id = :requisitoId " +
            "AND caracteristica_id = :caracteristicaId ", nativeQuery = true)
    void updateCauseErrorOfCharacteristic(@Param("dde") boolean dde,
                                          @Param("dii") boolean dii,
                                          @Param("var") boolean var,
                                          @Param("requisitoId") Integer requirementId,
                                          @Param("caracteristicaId") Integer characteristicId);

    @Query(value = "SELECT COUNT(r.id) " +
            "FROM requisito r " +
            "INNER JOIN tipo_error_caracteristica tec ON r.id = tec.requisito_id " +
            "INNER JOIN caracteristica c ON c.id = tec.caracteristica_id " +
            "WHERE (:tipoRequisito = '' OR r.tipo_requisito = :tipoRequisito) " +
            "AND (c.nombre = :nombreCaracteristica OR c.nombre_opuesto = :nombreCaracteristica)" +
            "AND (r.proyecto_id = :proyectoId)", nativeQuery = true)
    int countRequirementsByTypeAndNameCharacteristic(@Param("tipoRequisito") String typeRequirement,
                                                     @Param("nombreCaracteristica") String nameCharacteristic,
                                                     @Param("proyectoId") Integer projectId);

    @Query(value = "SELECT COUNT(r.id) " +
            "FROM requisito r " +
            "INNER JOIN tipo_error_caracteristica tec ON r.id = tec.requisito_id " +
            "WHERE (r.id = :requisitoId) " +
            "AND " +
            "(" +
            "   (:causaError = 'dii' AND tec.dii = true) OR " +
            "   (:causaError = 'dde' AND tec.dde = true) OR " +
            "   (:causaError = 'var' AND tec.var = true)" +
            ") " +
            "AND (r.proyecto_id = :proyectoId)", nativeQuery = true)
    int countRequirementsByRequirementIdAndCauseError(@Param("requisitoId") Integer requirementId,
                                                      @Param("causaError") String causeError,
                                                      @Param("proyectoId") Integer projectId);

}