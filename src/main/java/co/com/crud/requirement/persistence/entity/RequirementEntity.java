package co.com.crud.requirement.persistence.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "requisito")
public class RequirementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @Column(unique = true, nullable = false)
    private Integer proyectoId;

    @Column(unique = true, nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private String tipoRequisito;

    @OneToMany(mappedBy = "requisito", targetEntity = CharacteristicEntity.class,
            fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private List<CharacteristicEntity> characteristics;

    @OneToOne(mappedBy = "requisito", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private TypeErrorCharacterEntity typeErrorCharacterEntity;
}