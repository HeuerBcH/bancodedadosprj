package com.projetobancodedados.projetobd.model;

import jakarta.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "timesheet")
public class Apontamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer timesheetId;

    @Column(name = "start_time")
    private Time startTime;

    @Column(name = "end_time")
    private Time endTime;

    @Column(name = "justification_letter")
    private String justificationLetter;

    @Temporal(TemporalType.DATE)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Funcionario employee;

    @ManyToOne
    @JoinColumn(name = "activity_id", nullable = false)
    private Atividade activity;

    @ManyToOne
    @JoinColumn(name = "contract_id", nullable = false)
    private Contrato contract;

    // Getters e Setters

    public Integer getTimesheetId() {
        return timesheetId;
    }

    public void setTimesheetId(Integer timesheetId) {
        this.timesheetId = timesheetId;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public String getJustificationLetter() {
        return justificationLetter;
    }

    public void setJustificationLetter(String justificationLetter) {
        this.justificationLetter = justificationLetter;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Funcionario getEmployee() {
        return employee;
    }

    public void setEmployee(Funcionario employee) {
        this.employee = employee;
    }

    public Atividade getActivity() {
        return activity;
    }

    public void setActivity(Atividade activity) {
        this.activity = activity;
    }

    public Contrato getContract() {
        return contract;
    }

    public void setContract(Contrato contract) {
        this.contract = contract;
    }
}