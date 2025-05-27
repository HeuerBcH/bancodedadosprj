package com.projetobancodedados.projetobd.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Feriado")
public class Feriado {

    @Id
    @Column(name = "id_feriado")
    private Integer idFeriado;

    @Column(name = "nome_feriado", length = 30)
    private String nomeFeriado;

    @Column(name = "data_feriado")
    private LocalDate dataFeriado;

    @Column(name = "permite_lancamento")
    private Boolean permiteLancamento;

    // Getters e Setters
    public Integer getIdFeriado() {
        return idFeriado;
    }
    public void setIdFeriado(Integer idFeriado) {
        this.idFeriado = idFeriado;
    }

    public String getNomeFeriado() {
        return nomeFeriado;
    }
    public void setNomeFeriado(String nomeFeriado) {
        this.nomeFeriado = nomeFeriado;
    }

    public LocalDate getDataFeriado() {
        return dataFeriado;
    }
    public void setDataFeriado(LocalDate dataFeriado) {
        this.dataFeriado = dataFeriado;
    }

    public Boolean getPermiteLancamento() {
        return permiteLancamento;
    }
    public void setPermiteLancamento(Boolean permiteLancamento) {
        this.permiteLancamento = permiteLancamento;
    }
}
