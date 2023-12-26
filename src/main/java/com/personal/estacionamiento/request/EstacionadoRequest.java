package com.personal.estacionamiento.request;

import com.personal.estacionamiento.dto.EstacionamientoDto;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.sql.Timestamp;
import java.util.Date;

public class EstacionadoRequest {

    private long id;
    private Timestamp fechaIngreso;
    private Timestamp fechaSalida;
    private long valorTotal;
    private int estado;
    private String patente;
    private long estacionamiento_id;

    private int tipoPago;

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

    public long getEstacionamiento_id() {
        return estacionamiento_id;
    }

    public void setEstacionamiento_id(long estacionamiento_id) {
        this.estacionamiento_id = estacionamiento_id;
    }

    public int getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(int tipoPago) {
        this.tipoPago = tipoPago;
    }
}
