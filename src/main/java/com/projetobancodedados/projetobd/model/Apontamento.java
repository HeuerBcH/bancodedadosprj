package com.projetobancodedados.projetobd.model;

import jakarta.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
public class Apontamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_apontamento;

    @Column(nullable = false)
    private Time hora_inicio;

    @Column(nullable = false)
    private Time hora_fim;

    @Column(length = 30)
    private String centro_de_custo;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data_apontamento;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data_preenchimento;

    @Column(nullable = false)
    private Boolean aprovado = false;

    @ManyToOne
    @JoinColumn(name = "fk_Atividade_id_atividade", nullable = false)
    private Atividade atividade;

    @ManyToOne
    @JoinColumn(name = "fk_Funcionario_id_funcionario", nullable = false)
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "fk_Feriado_id_feriado", nullable = true)
    private Feriado feriado;

    // Getters e Setters

    public Integer getId_apontamento() { return id_apontamento; }
    public void setId_apontamento(Integer id_apontamento) { this.id_apontamento = id_apontamento; }

    public Time getHora_inicio() { return hora_inicio; }
    public void setHora_inicio(Time hora_inicio) { this.hora_inicio = hora_inicio; }

    public Time getHora_fim() { return hora_fim; }
    public void setHora_fim(Time hora_fim) { this.hora_fim = hora_fim; }

    public String getCentro_de_custo() { return centro_de_custo; }
    public void setCentro_de_custo(String centro_de_custo) { this.centro_de_custo = centro_de_custo; }

    public Date getData_apontamento() { return data_apontamento; }
    public void setData_apontamento(Date data_apontamento) { this.data_apontamento = data_apontamento; }

    public Date getData_preenchimento() { return data_preenchimento; }
    public void setData_preenchimento(Date data_preenchimento) { this.data_preenchimento = data_preenchimento; }

    public Boolean getAprovado() { return aprovado; }
    public void setAprovado(Boolean aprovado) { this.aprovado = aprovado; }

    public Atividade getAtividade() { return atividade; }
    public void setAtividade(Atividade atividade) { this.atividade = atividade; }

    public Funcionario getFuncionario() { return funcionario; }
    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }

    public Feriado getFeriado() { return feriado; }
    public void setFeriado(Feriado feriado) { this.feriado = feriado; }
}