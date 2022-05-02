package com.example.challengue.Entities;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserVaccineKey implements Serializable {

    @Column(name = "user_id")
    private String userId;

    @Column(name = "vaccine_id")
    private Integer vaccineId;
}
