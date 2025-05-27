package com.projetobancodedados.projetobd.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_funcionario;

    @JsonProperty("fk_users_id_user")
    @Column(name = "fk_users_id_user", nullable = false)
    private Integer fkUsersIdUser;

    @Column(name = "nome", length = 45)
    private String nome;

    // Se quiser mapear a relação com Users (opcional, mas recomendado):
    // @ManyToOne
    // @JoinColumn(name = "fk_users_id_user", referencedColumnName = "id_user", insertable = false, updatable = false)
    // private Users user;

    // -------------------
    public Integer getId_funcionario() {
        return id_funcionario;
    }
    public void setId_funcionario(Integer id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public Integer getFkUsersIdUser() {
        return fkUsersIdUser;
    }
    public void setFkUsersIdUser(Integer fkUsersIdUser) {
        this.fkUsersIdUser = fkUsersIdUser;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
}
