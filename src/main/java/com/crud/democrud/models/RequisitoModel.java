package com.crud.democrud.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "requisito")
public class RequisitoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;
    public RequisitoModel(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    public RequisitoModel() {

    }
}