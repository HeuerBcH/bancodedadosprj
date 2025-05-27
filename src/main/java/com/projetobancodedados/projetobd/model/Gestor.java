package com.projetobancodedados.projetobd.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Gestor {

    @Id
    @Column(name = "id_gestor")
    private Integer idGestor;

    @Column(name = "grupo_gerido", nullable = true, length = 30)
    private String grupoGerido;

    @OneToOne
    @JoinColumn(name = "id_gestor", referencedColumnName = "id_funcionario", 
        foreignKey = @ForeignKey(name = "fk_gestor_funcionario", 
        foreignKeyDefinition = "FOREIGN KEY (id_gestor) REFERENCES Funcionario(id_funcionario) ON DELETE CASCADE ON UPDATE CASCADE"))
    private Funcionario funcionario;

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

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
