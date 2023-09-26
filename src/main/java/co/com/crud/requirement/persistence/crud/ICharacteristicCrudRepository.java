package co.com.crud.requirement.persistence.crud;

import co.com.crud.requirement.domain.model.queryresult.ICharacteristicByRequirement;
import co.com.crud.requirement.domain.model.queryresult.ICharacteristicGrade;
import co.com.crud.requirement.persistence.entity.CharacteristicEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ICharacteristicCrudRepository extends CrudRepository<CharacteristicEntity, Integer> {

    Optional<CharacteristicEntity> findById(Integer id);

    @Query(value = "SELECT * FROM buscar_caracteristicas_por_requisito();", nativeQuery = true)
    List<ICharacteristicByRequirement> findCharacteristicsByRequirement();

    @Query(value = "SELECT * FROM buscar_notas_caracteristica();", nativeQuery = true)
    List<ICharacteristicGrade> findGadesCharacteristic();
}