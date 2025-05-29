package com.projetobancodedados.projetobd.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Funcionario_Escala")
public class FuncionarioEscala {

    @Embeddable
    public static class FuncionarioEscalaId implements Serializable {
        @Column(name = "fk_Funcionario_id_funcionario")
        private Integer funcionarioId;

        @Column(name = "fk_Escala_de_Trabalho_id_escala_de_trabalho")
        private Integer escalaDeTrabalhoId;

        public FuncionarioEscalaId() {}
        public FuncionarioEscalaId(Integer funcionarioId, Integer escalaDeTrabalhoId) {
            this.funcionarioId = funcionarioId;
            this.escalaDeTrabalhoId = escalaDeTrabalhoId;
        }
        public Integer getFuncionarioId() { return funcionarioId; }
        public void setFuncionarioId(Integer funcionarioId) { this.funcionarioId = funcionarioId; }
        public Integer getEscalaDeTrabalhoId() { return escalaDeTrabalhoId; }
        public void setEscalaDeTrabalhoId(Integer escalaDeTrabalhoId) { this.escalaDeTrabalhoId = escalaDeTrabalhoId; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof FuncionarioEscalaId)) return false;
            FuncionarioEscalaId that = (FuncionarioEscalaId) o;
            return Objects.equals(funcionarioId, that.funcionarioId) &&
                   Objects.equals(escalaDeTrabalhoId, that.escalaDeTrabalhoId);
        }
        @Override
        public int hashCode() {
            return Objects.hash(funcionarioId, escalaDeTrabalhoId);
        }
    }

    @EmbeddedId
    private FuncionarioEscalaId id;

    @ManyToOne
    @MapsId("funcionarioId")
    @JoinColumn(name = "fk_Funcionario_id_funcionario", referencedColumnName = "id_funcionario")
    private Funcionario funcionario;

    @ManyToOne
    @MapsId("escalaDeTrabalhoId")
    @JoinColumn(name = "fk_Escala_de_Trabalho_id_escala_de_trabalho", referencedColumnName = "id_escala_de_trabalho")
    private EscalaDeTrabalho escalaDeTrabalho;

    // Getters e setters
    public FuncionarioEscalaId getId() { return id; }
    public void setId(FuncionarioEscalaId id) { this.id = id; }

    public Funcionario getFuncionario() { return funcionario; }
    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }

    public EscalaDeTrabalho getEscalaDeTrabalho() { return escalaDeTrabalho; }
    public void setEscalaDeTrabalho(EscalaDeTrabalho escalaDeTrabalho) { this.escalaDeTrabalho = escalaDeTrabalho; }
}
