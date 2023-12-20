package co.com.crud.requirement.persistence.crud;

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
            "((:causaError = 'dii' AND tec.dii = true) OR " +
            "   (:causaError = 'dde' AND tec.dde = true) OR " +
            "   (:causaError = 'var' AND tec.var = true))",
            nativeQuery = true)
    int countRequirementsByTypeAndCauseError(
            @Param("tipoRequisito") String typeRequirement,
            @Param("causaError") String causeError);

    @Query(value = "SELECT COUNT(*)  " +
            "FROM tipo_error_caracteristica " +
            "WHERE (requisito_id = :requisitoId)  AND dde = 'true' AND (:causaError = 'dde') OR " +
            "(requisito_id = :requisitoId)  AND dii = 'true' AND (:causaError = 'dii') OR" +
            "(requisito_id = :requisitoId)  AND var = 'true' AND (:causaError = 'var')", nativeQuery = true)
    int countRequirementsByCauseErrorAndRequirementId(
            @Param("requisitoId") Integer requirementId,
            @Param("causaError") String causeError);


    // Para dar el valor de los requisitos que tienen tipo error DDE en true
    @Query(value = "SELECT count(*) " +
            "FROM tipo_error_caracteristica " +
            "WHERE dde = 'true' ", nativeQuery = true)
    int countRequirementsByCauseErrorDDE();

    // Para dar el valor de los requisitos que tienen tipo error DII en true
    @Query(value = "SELECT count(*) " +
            "FROM tipo_error_caracteristica " +
            "WHERE dii = 'true' ", nativeQuery = true)
    int countRequirementsByCauseErrorDII();


    // Para dar el valor de los requisitos que tienen tipo error VAR en true
    @Query(value = "SELECT count(*) " +
            "FROM tipo_error_caracteristica " +
            "WHERE var = 'true' ", nativeQuery = true)
    int countRequirementsByCauseErrorVAR();

    // Contar cuantos requisitos tienen tipo de error EIE por id de requisito
    @Query(value = "SELECT count(*) " +
            "FROM tipo_error_caracteristica " +
            "WHERE tipo_error_id = 1 and requisito_id = :requisitoId", nativeQuery = true)
    int countTypeErrorEIEByRequirement(@Param("requisitoId") Integer requirementId);

    // Contar cuantos requisitos tienen tipo de error MCC por id de requisito
    @Query(value = "SELECT count(*) " +
            "FROM tipo_error_caracteristica " +
            "WHERE tipo_error_id = 2 and requisito_id = :requisitoId", nativeQuery = true)
    int countTypeErrorMCCByRequirement(@Param("requisitoId") Integer requirementId);

    //MCC y EIE dinamico -- Para contar cuantos hay MCC y EIE por id de requisito
    @Query(value = "SELECT count(*) " +
            "FROM tipo_error_caracteristica " +
            "WHERE tipo_error_id = :typeErrorId and requisito_id = :requisitoId", nativeQuery = true)
    int countTypeErrorsByRequirements(@Param("typeErrorId") Integer typeErrorId,
                                      @Param("requisitoId") Integer requirementId);

    // Para contar los tipos de error por requisito
    @Query(value = "SELECT count(*) " +
            "FROM tipo_error_caracteristica " +
            "WHERE tipo_error_id = :requisitoId", nativeQuery = true)
    int countTypeErrorsByRequirement(@Param("requisitoId") Integer requirementId);

    //Para contar todos las causas de error por requsiito En true
    @Query(value = "SELECT count(*) " +
            "FROM tipo_error_caracteristica " +
            "WHERE dde = 'true' and  dii = 'true' and var = 'true' and tipo_error_id = :requisitoId", nativeQuery = true)
    int countCauseErrorsByRequirement(@Param("requisitoId") Integer requirementId);


}