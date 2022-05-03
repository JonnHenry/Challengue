package com.example.challengue.DTO;

import lombok.Data;

import javax.validation.constraints.NotEmpty;


/**
 * This class is to create an object that carries data between processes.
 * The specific process that use this class is create a rol
 * @author Jonnathan Campoberde
 * @version 1
 */

@Data
public class RolDTO {


    private Integer id;

    @NotEmpty(message = "El rol no puede ser vacio o nulo")
    private String rol;

    private boolean isActive;

}
