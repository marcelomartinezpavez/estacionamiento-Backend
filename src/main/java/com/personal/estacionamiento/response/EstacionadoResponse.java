package com.personal.estacionamiento.response;

import com.personal.estacionamiento.dto.EstacionamientoDto;

import javax.persistence.*;
import java.sql.Timestamp;

public class EstacionadoResponse {
    private long id;

    private Timestamp fechaIngreso;

    private Timestamp fechaSalida;

    private long valorTotal;

    private int tipoPago;

    private int estado;

    private String patente;

    private long estacionamientoId;

    private long minutosEstacionado;

    private EstacionamientoResponse estacionamiento;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Timestamp getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Timestamp fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Timestamp getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Timestamp fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public long getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(long valorTotal) {
        this.valorTotal = valorTotal;
    }

    public int getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(int tipoPago) {
        this.tipoPago = tipoPago;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public long getEstacionamientoId() {
        return estacionamientoId;
    }

    public void setEstacionamientoId(long estacionamientoId) {
        this.estacionamientoId = estacionamientoId;
    }

    public long getMinutosEstacionado() {
        return minutosEstacionado;
    }

    public void setMinutosEstacionado(long minutosEstacionado) {
        this.minutosEstacionado = minutosEstacionado;
    }

    public EstacionamientoResponse getEstacionamiento() {
        return estacionamiento;
    }

    public void setEstacionamiento(EstacionamientoResponse estacionamiento) {
        this.estacionamiento = estacionamiento;
    }
}
