package com.example.challengue.DTO;


import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * This class is to create a object that carries data between processes.
 * The process is register a vaccine
 * @author Jonnathan Campoberde
 * @version 1
 */



@Data
public class VaccineDTO {

    private Integer id;

    @NotEmpty(message = "El nombre de la vacuna no puede ser vacion o nulo")
    private String nameVaccine;

    private Boolean isActive;


}
