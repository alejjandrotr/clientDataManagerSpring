package com.alejjandrodev.clientDataManager.models;

import com.alejjandrodev.clientDataManager.common.ValidationGroups;
import jakarta.persistence.*;

import jakarta.validation.constraints.*;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public abstract class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(groups = ValidationGroups.Create.class)
    @Column(nullable = false, unique = true)
    private String nombre;

    @Size(min = 1)
    @Column(nullable = true)
    private String genero;

    @NotBlank(groups = ValidationGroups.Create.class)
    @Size(min = 5, max = 100)
    @Column(nullable = false)
    private String direccion;

    @NotBlank(groups = ValidationGroups.Create.class)
    @Size(min = 5, max = 100)
    @Column(nullable = false)
    private String telefono;

    @Size( min = 1, max = 50)
    @Column(nullable = true)
    private String identificacion;


    @Min(1)
    @Column(nullable = true)
    private Integer edad;

    public Persona() {
    }
    public Persona(Long id, String nombre, String genero, String direccion, String telefono, String identificacion, Integer edad) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.direccion = direccion;
        this.telefono = telefono;
        this.identificacion = identificacion;
        this.edad = edad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

}
