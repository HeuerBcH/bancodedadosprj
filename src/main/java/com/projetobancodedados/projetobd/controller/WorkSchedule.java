package com.projetobancodedados.projetobd.controller;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;

@Entity
@Data
public class WorkSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer workScheduleId;

    @Column(nullable = false)
    private Time contractHours;

    @Column(nullable = false)
    private String weekday;

    // Getters and Setters
    public Integer getWorkScheduleId() {
        return workScheduleId;
    }

    public void setWorkScheduleId(Integer workScheduleId) {
        this.workScheduleId = workScheduleId;
    }

    public Time getContractHours() {
        return contractHours;
    }

    public void setContractHours(Time contractHours) {
        this.contractHours = contractHours;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }
}
