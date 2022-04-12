package com.personal.estacionamiento.request;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class ConfiguracionRequest {

    private int habilitado;
    private long valorMinuto;
    private long valorHora;
    private long valorDia;
    private long valorMes;
    private long empresa_id;
    private long estacionamiento_id;

    public long getEstacionamiento_id() {
        return estacionamiento_id;
    }

    public void setEstacionamiento_id(long estacionamiento_id) {
        this.estacionamiento_id = estacionamiento_id;
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

    public long getEmpresa_id() {
        return empresa_id;
    }

    public void setEmpresa_id(long empresa_id) {
        this.empresa_id = empresa_id;
    }
}
