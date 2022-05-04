package com.example.challengue.DTO.Response;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date birthDate;

    private String address;

    private String telephone;


}
