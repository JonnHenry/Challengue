package com.example.challengue.Entities;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "users_vaccines")
@Data
public class UserVaccine implements Serializable {

    @EmbeddedId
    private UserVaccineKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("vaccineId")
    @JoinColumn(name = "vaccine_id")
    private Vaccine vaccine;

    private Integer numberDoses;

    private Date vaccinationDate;

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
