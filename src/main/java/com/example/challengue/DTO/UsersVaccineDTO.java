package com.example.challengue.DTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
public class UsersVaccineDTO {

    @NotEmpty(message = "La cédula no puede estar vacia o nula")
    private String id;

    private String username;

    private String address;

    private String telephone;

    private String nameVaccine;

    private Date dateVaccination;

    private int numberDoses;
}
