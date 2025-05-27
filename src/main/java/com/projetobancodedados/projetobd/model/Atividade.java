package com.projetobancodedados.projetobd.model;

import jakarta.persistence.*;
// import lombok.Data;

@Entity
// @Data
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_atividade;

    @Column(name = "description", nullable = false, length = 400)
    private String descricao;

    @Column(name = "internal", nullable = false)
    private Boolean interna;

    // -------------------
    public Integer getId_atividade() {
        return id_atividade;
    }
    public void setId_atividade(Integer id_atividade) {
        this.id_atividade = id_atividade;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public Boolean getInterna() {
        return interna;
    }
    public void setInterna(Boolean interna) {
        this.interna = interna;
    }
}
