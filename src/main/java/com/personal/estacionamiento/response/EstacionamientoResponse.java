package com.personal.estacionamiento.response;

import com.personal.estacionamiento.dto.EmpresaDto;
import com.personal.estacionamiento.dto.EstacionadoDto;

import javax.persistence.*;
import java.util.Set;

public class EstacionamientoResponse {
    private long id;

    private int cantidadTotal;

    private int cantidadLibre;
    private int cantidadOcupado;

    private int habilitado;

    //private Set<EstacionadoDto> estacionado;

    //private EmpresaDto empresa;


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
}
