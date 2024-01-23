package co.com.crud.requirement.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "operacion")
public class OperationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @Column(nullable = false)
    private Integer requisitoId;

    @Column(nullable = false)
    private Double nivelAdecuacion;

    @Column(nullable = false)
    private Double caracteristicasEvaluadas;

    @Column(nullable = false)
    private Double nivelCaracteristicaPuntuacion;

    @Column(nullable = false)
    private Double puntajeMaximo;

    @Column(nullable = false)
    private Double promedioCalculado;
}
