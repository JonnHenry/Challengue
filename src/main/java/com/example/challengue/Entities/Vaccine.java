package com.example.challengue.Entities;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "vaccines")
@Data

public class Vaccine implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(unique = true)
    private String VaccineName;

    @Column(columnDefinition = "boolean default true")
    private Boolean isActive;

    @Temporal(TemporalType.TIMESTAMP)
    @Column( nullable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column( nullable = false)
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        updatedAt = createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

}