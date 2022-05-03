package com.example.challengue.Entities;


import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "username" }, name = "UniqueUsername"),
        @UniqueConstraint(columnNames = { "email" },name = "UniqueEmail")
})
@Data
public class User implements Serializable {

    @Id
    @NotNull
    @Pattern(regexp="[\\d]{10}", message="La cédula ingresada no es valida verifique si la cantidad de digitos numericos ingresados es igual a 10")
    private String id;

    @NotNull
    @Column(nullable = false)
    private String names;

    @NotNull
    @Column(nullable = false)
    private String surnames;

    @NotBlank
    @Email
    @Column(nullable = false)
    private String email;


    private Date birthDate;


    private String address;


    private String telephone;

    @NotNull
    @Column(nullable = false)
    private String userName;

    @NotNull
    @Column(nullable = false)
    private String password;

    @Column(columnDefinition = "boolean default true")
    private Boolean isActive;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            uniqueConstraints = {
                    @UniqueConstraint(columnNames = { "user_id","rol_id" }, name = "UniqueUsername")},
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "rol_id",referencedColumnName = "id"
            )
    )
    private Collection<Rol> roles = new HashSet<>();

    /*OneToMany(mappedBy = "user")
    private Set<UserVaccine> userVaccines;*/

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updatedAt;

    public User(String id, String names, String surnames, String email, String password, Collection<Rol> roles) {
        super();
        this.id = id;
        this.names = names;
        this.surnames = surnames;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.isActive = true;
    }

    public User(String names, String surnames, String email, String password, Collection<Rol> roles) {
        super();
        this.names = names;
        this.surnames = surnames;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.isActive = true;
    }


    public User(String email, String password, Collection<Rol> roles) {
        super();
        this.names = names;
        this.surnames = surnames;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.isActive = true;
    }

    public User(){
        this.isActive = true;
    }

    @PrePersist
    protected void onCreate() {
        updatedAt = createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date();
    }
}
