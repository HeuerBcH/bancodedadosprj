package com.projetobancodedados.projetobd.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Gestor")
public class Gestor {

    @Id
    @Column(name = "id_gestor")
    private Integer idGestor;

    @Column(name = "grupo_gerido", length = 30)
    private String grupoGerido;

    // Getters e Setters
    public Integer getIdGestor() {
        return idGestor;
    }
    public void setIdGestor(Integer idGestor) {
        this.idGestor = idGestor;
    }

    public String getGrupoGerido() {
        return grupoGerido;
    }
    public void setGrupoGerido(String grupoGerido) {
        this.grupoGerido = grupoGerido;
    }

    public Integer getId_gestor() {
        return idGestor;
    }

    public void setId_gestor(Integer id_gestor) {
        this.idGestor = id_gestor;
    }
}
