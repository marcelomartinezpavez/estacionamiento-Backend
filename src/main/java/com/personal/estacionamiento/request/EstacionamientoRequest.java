package com.personal.estacionamiento.request;

import com.personal.estacionamiento.dto.EmpresaDto;
import com.personal.estacionamiento.dto.EstacionadoDto;

import javax.persistence.*;
import java.util.Set;

public class EstacionamientoRequest {
    private long id;
    private int cantidadTotal;
    private int cantidadLibre;
    private int cantidadOcupado;
    private int habilitado;
    private long empresa_id;

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

    public int getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(int habilitado) {
        this.habilitado = habilitado;
    }

    public long getEmpresa_id() {
        return empresa_id;
    }

    public void setEmpresa_id(long empresa_id) {
        this.empresa_id = empresa_id;
    }
}
