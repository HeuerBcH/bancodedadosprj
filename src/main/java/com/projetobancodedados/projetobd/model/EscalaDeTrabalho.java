package com.projetobancodedados.projetobd.model;

import jakarta.persistence.*;

@Entity

public class EscalaDeTrabalho {
    @Id
    // Gera o valor automaticamente com base na estratégia do banco
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_escala_de_trabalho;

    @Column(nullable = false)
    private Integer horas_semanais;

    @Column(nullable = false, length = 100)
    private String dias_semana;

    // -------------------

    public Integer getId_escala_de_trabalho() {
        return id_escala_de_trabalho;
    }

    public void setId_escala_de_trabalho(Integer id_escala_de_trabalho) {
        this.id_escala_de_trabalho = id_escala_de_trabalho;
    }

    public Integer getHoras_semanais() {
        return horas_semanais;
    }

    public void setHoras_semanais(Integer horas_semanais) {
        this.horas_semanais = horas_semanais;
    }

    public String getDias_semana() {
        return dias_semana;
    }

    public void setDias_semana(String dias_semana) {
        this.dias_semana = dias_semana;
    }
	
}
