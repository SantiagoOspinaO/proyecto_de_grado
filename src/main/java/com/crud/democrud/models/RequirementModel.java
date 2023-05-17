package com.crud.democrud.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "requisito")
public class RequirementModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Integer id;

    @Column(name = "nombre", unique = true, nullable = false)
    private String nombre;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

//    @Column(name = "tipo_requisito", nullable = false)
//    private String tipoRequisito;

}