package co.com.crud.requirement.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "requisito", uniqueConstraints = @UniqueConstraint(columnNames = {"proyectoId", "nombre"}))
public class RequirementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @Column(nullable = false)
    private Integer proyectoId;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 3000)
    private String descripcion;

    @Column(nullable = false, length = 12)
    private String tipoRequisito;

}