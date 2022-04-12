package com.personal.estacionamiento.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "configuracion")
public class ConfiguracionDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "habilitado")
    private int habilitado;

    @Column(name = "valor_minuto")
    private long valorMinuto;

    @Column(name = "valor_hora")
    private long valorHora;

    @Column(name = "valor_dia")
    private long valorDia;

    @Column(name = "valor_mes")
    private long valorMes;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    private EmpresaDto empresa;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estacionamiento_id",insertable = false,updatable = false)
    private EstacionamientoDto estacionamiento;


    @Column(name = "estacionamiento_id")
    private long estacionamientoId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(int habilitado) {
        this.habilitado = habilitado;
    }

    public long getValorMinuto() {
        return valorMinuto;
    }

    public void setValorMinuto(long valorMinuto) {
        this.valorMinuto = valorMinuto;
    }

    public long getValorHora() {
        return valorHora;
    }

    public void setValorHora(long valorHora) {
        this.valorHora = valorHora;
    }

    public long getValorDia() {
        return valorDia;
    }

    public void setValorDia(long valorDia) {
        this.valorDia = valorDia;
    }

    public long getValorMes() {
        return valorMes;
    }

    public void setValorMes(long valorMes) {
        this.valorMes = valorMes;
    }

    public long getEstacionamientoId() {
        return estacionamientoId;
    }

    public void setEstacionamientoId(long estacionamientoId) {
        this.estacionamientoId = estacionamientoId;
    }


    public EmpresaDto getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaDto empresa) {
        this.empresa = empresa;
    }

    public EstacionamientoDto getEstacionamiento() {
        return estacionamiento;
    }

    public void setEstacionamiento(EstacionamientoDto estacionamiento) {
        this.estacionamiento = estacionamiento;
    }
}