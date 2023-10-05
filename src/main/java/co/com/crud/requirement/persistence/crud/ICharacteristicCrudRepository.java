package co.com.crud.requirement.persistence.crud;

import co.com.crud.requirement.domain.model.queryresult.ICharacteristicByRequirement;
import co.com.crud.requirement.domain.model.queryresult.IGradeOfCharacteristic;
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

    @Query(value = "SELECT req.id AS id, car.nombre AS nameCharac, car.descripcion AS description " +
            " FROM requisito req " +
            " INNER JOIN requisito_caracteristica_nota reqca ON req.id = reqca.requisito_id " +
            " INNER JOIN caracteristica car ON reqca.caracteristica_id = car.id ", nativeQuery = true)
    List<ICharacteristicByRequirement> findCharacteristicsByRequirement();

    @Query(value = "SELECT nota_caracteristica AS grade " +
            " FROM requisito_caracteristica_nota ", nativeQuery = true)
    List<IGradeOfCharacteristic> findGradesOfCharacteristics();

    @Modifying
    @Transactional
    @Query(value = "SELECT actualizar_nota_caracteristica(:requisitoId, :caracteristicaId, :notaIngresada)", nativeQuery = true)
    void updateGradeOfCharacteristic(@Param("requisitoId") Integer requirementId, @Param("caracteristicaId") Integer characteristicId, @Param("notaIngresada") Double gradeInput);
}