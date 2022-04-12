package com.personal.estacionamiento.dto;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class UsersDto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    private EmpresaDto empresa;

    @Column(name = "users")
    private String users;

    @Column(name = "pass")
    private String pass;

    @Column(name = "rol")
    private String rol;

    @Column(name = "habilitado")
    private int habilitado;

    public UsersDto(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public EmpresaDto getEmpresa() {
        return empresa;
    }

    public void setEmpresa(EmpresaDto empresa) {
        this.empresa = empresa;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public int getHabilitado() {
        return habilitado;
    }

    public void setHabilitado(int habilitado) {
        this.habilitado = habilitado;
    }
}
