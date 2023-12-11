package co.com.crud.requirement.persistence.crud;

import co.com.crud.requirement.persistence.entity.TypeErrorCharacteristicEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface ITypeErrorCharacteristicCrudRepository extends CrudRepository<TypeErrorCharacteristicEntity, Integer> {

    Optional<TypeErrorCharacteristicEntity> findById(Integer id);

    @Query(value = "SELECT contar_registros_tipo_error(:tipoRequisito, :tipoError)", nativeQuery = true)
    int countRequirementsByTypeAndError(@Param("tipoRequisito") String typeRequirement,
                                        @Param("tipoError") String typeError);

    @Query(value = "SELECT count(*) " +
            "FROM tipo_error_caracteristica " +
            "WHERE dde = 'true' ", nativeQuery = true)
    int countRequirementsByErrorDDE();

    @Query(value = "SELECT count(*) " +
            "FROM tipo_error_caracteristica " +
            "WHERE dii = 'true' ", nativeQuery = true)
    int countRequirementsByErrorDII();

    @Query(value = "SELECT count(*) " +
            "FROM tipo_error_caracteristica " +
            "WHERE var = 'true' ", nativeQuery = true)
    int countRequirementsByErrorVAR();



}