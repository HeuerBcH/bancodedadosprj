package com.projetobancodedados.projetobd.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Contrato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contractId;

    @Column(nullable = false)
    private String activityDescription;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Cliente client;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Funcionario employee;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Gestor manager;

    // Getters and setters manuais
    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Cliente getClient() {
        return client;
    }

    public void setClient(Cliente client) {
        this.client = client;
    }

    public Funcionario getEmployee() {
        return employee;
    }

    public void setEmployee(Funcionario employee) {
        this.employee = employee;
    }

    public Gestor getManager() {
        return manager;
    }

    public void setManager(Gestor manager) {
        this.manager = manager;
    }
}