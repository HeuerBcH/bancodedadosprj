package com.projetobancodedados.projetobd.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_contrato;

    @Column(nullable = false, length = 255)
    private String obj_contratado;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data_inicio;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data_fim;

    @Column(nullable = false, length = 50)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "fk_Cliente_id_cliente", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "fk_Funcionario_id_funcionario", nullable = false)
    private Funcionario funcionario;

    @ManyToOne
    @JoinColumn(name = "fk_Gestor_id_gestor", nullable = false)
    private Gestor gestor;

    // Getters e Setters

    public Integer getId_contrato() { return id_contrato; }
    public void setId_contrato(Integer id_contrato) { this.id_contrato = id_contrato; }

    public String getObj_contratado() { return obj_contratado; }
    public void setObj_contratado(String obj_contratado) { this.obj_contratado = obj_contratado; }

    public Date getData_inicio() { return data_inicio; }
    public void setData_inicio(Date data_inicio) { this.data_inicio = data_inicio; }

    public Date getData_fim() { return data_fim; }
    public void setData_fim(Date data_fim) { this.data_fim = data_fim; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Funcionario getFuncionario() { return funcionario; }
    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }

    public Gestor getGestor() { return gestor; }
    public void setGestor(Gestor gestor) { this.gestor = gestor; }
}