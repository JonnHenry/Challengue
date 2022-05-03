package com.example.challengue.DTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * This class is to create an object that carries data between processes.
 * The specific process that use this class is Register a user and vaccine by the user
 * @author Jonnathan Campoberde
 * @version 1
 */

@Data
public class UserRegisterVaccineDTO {

    @NotEmpty(message = "El nombre de usuario no puede ser vacio")
    private String username;

    private Integer idVaccine;

    private Date dateVaccination;

    private int numberDoses;
}
