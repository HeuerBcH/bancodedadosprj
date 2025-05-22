package com.projetobancodedados.projetobd.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer activityId;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Boolean internal;

    // Getters and setters
    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getInternal() {
        return internal;
    }

    public void setInternal(Boolean internal) {
        this.internal = internal;
    }

}
