package com.personal.estacionamiento.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "estacionamiento")
public class EstacionamientoDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "cantidadTotal")
    private int cantidadTotal;
    @Column(name = "cantidadLibre")
    private int cantidadLibre;
    @Column(name = "cantidadOcupado")
    private int cantidadOcupado;

    @Column(name = "habilitado")
    private int habilitado;

    @OneToMany(fetch = FetchType.EAGER)
    @Column(name = "estacionado")
    private Set<EstacionadoDto> estacionado;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    private EmpresaDto empresa;


    public EmpresaDto getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaDto empresa) {
        this.empresa = empresa;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public int getCantidadLibre() {
        return cantidadLibre;
    }

    public void setCantidadLibre(int cantidadLibre) {
        this.cantidadLibre = cantidadLibre;
    }

    public int getCantidadOcupado() {
        return cantidadOcupado;
    }

    public void setCantidadOcupado(int cantidadOcupado) {
        this.cantidadOcupado = cantidadOcupado;
    }

    public Set<EstacionadoDto> getEstacionado() {
        return estacionado;
    }

    public void setEstacionado(Set<EstacionadoDto> estacionado) {
        this.estacionado = estacionado;
    }

    public int getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(int habilitado) {
        this.habilitado = habilitado;
    }
}
