package com.personal.estacionamiento.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "empresa")
public class EmpresaDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "rut")
    private String rut;

    @OneToMany(fetch = FetchType.EAGER)
    @Column(name = "estacionamientos")
    private Set<EstacionamientoDto> estacionamientos;

    @OneToOne
    @JoinColumn(name = "id_configuracion")
    private ConfiguracionDto id_configuracion;


    public EmpresaDto(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public Set<EstacionamientoDto> getEstacionamientos() {
        return estacionamientos;
    }

    public void setEstacionamientos(Set<EstacionamientoDto> estacionamientos) {
        this.estacionamientos = estacionamientos;
    }

    public ConfiguracionDto getId_configuracion() {
        return id_configuracion;
    }

    public void setId_configuracion(ConfiguracionDto id_configuracion) {
        this.id_configuracion = id_configuracion;
    }

    /*public UsersDto getUsers() {
        return users;
    }

    public void setUsers(UsersDto users) {
        this.users = users;
    }*/
}
