package com.example.challengue.DTO.Response;


import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * This class is to create an object that carries data between processes.
 * The user's complete or update the register
 * @author Jonnathan Campoberde
 * @version 1
 */

@Data
public class UserCompleteRegisterDTO {

    @NotEmpty(message = "El nombre de usurio ni puede ser vacio")
    private String username;

    private Date birthDate;

    private String address;

    private String telephone;

}
