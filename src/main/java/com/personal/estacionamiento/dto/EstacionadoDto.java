package com.personal.estacionamiento.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "estacionado")
public class EstacionadoDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //@CreationTimestamp
    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_ingreso")
    private Timestamp fechaIngreso;

    @Column(name = "fecha_salida")
    private Timestamp fechaSalida;

    @Column(name = "valor_total")
    private long valorTotal;

    @Column(name = "estado")
    private int estado;

    @Column(name = "patente")
    private String patente;

    @Column(name = "estacionamiento_id")
    private long estacionamientoId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estacionamiento")
    private EstacionamientoDto estacionamiento;

    public EstacionamientoDto getEstacionamiento() {
        return estacionamiento;
    }

    public void setEstacionamiento(EstacionamientoDto estacionamiento) {
        this.estacionamiento = estacionamiento;
    }

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

    public long getEstacionamientoId() {
        return estacionamientoId;
    }

    public void setEstacionamientoId(long estacionamientoId) {
        this.estacionamientoId = estacionamientoId;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }
}
