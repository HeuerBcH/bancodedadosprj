package com.projetobancodedados.projetobd.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer managerId;

    @Column(nullable = false, length = 100)
    private String managerGroup;

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getManagerGroup() {
        return managerGroup;
    }

    public void setManagerGroup(String managerGroup) {
        this.managerGroup = managerGroup;
    }
}
