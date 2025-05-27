package com.projetobancodedados.projetobd.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;

    @Column(nullable = false, length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Gestor manager;

    // Manual getters and setters
    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Users getUsers() {
        return users;
    }

    public void setUser(Users users) {
        this.users = users;
    }

    public Gestor getManager() {
        return manager;
    }

    public void setManager(Gestor manager) {
        this.manager = manager;
    }
}
