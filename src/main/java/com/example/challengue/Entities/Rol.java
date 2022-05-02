package com.example.challengue.Entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "roles", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "rol" },name = "UniqueRol")
})
@Data
public class Rol implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(nullable = false)
    private String rol;

    @Column(columnDefinition = "boolean default true")
    private Boolean isActive;

    @Temporal(TemporalType.TIMESTAMP)
    @Column( nullable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updatedAt;

    public Rol() {

    }

    @PrePersist
    protected void onCreate() {
        updatedAt = createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }

    public Rol(String rol) {
        this.rol = rol;
    }
}
