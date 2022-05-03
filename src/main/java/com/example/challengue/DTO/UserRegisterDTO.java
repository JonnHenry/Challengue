package com.example.challengue.DTO;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


/**
 * This class is to create a object that carries data between processes.
 * The specific process that use this class is Register a user
 * @author Jonnathan Campoberde
 * @version 1
 */

@Data
public class UserRegisterDTO {

    @NotEmpty(message = "La cédula no puede estar vacia o nula")
    public String id;

    @NotEmpty(message = "Los nombres no pueden ser vacios o nulos")
    public String names;

    @NotEmpty(message = "Los apellidos no pueden ser vacios o nulos")
    public String surnames;

    @NotEmpty(message = "El email no debe ser vacio o nulo")
    @Email
    public String email;


}
