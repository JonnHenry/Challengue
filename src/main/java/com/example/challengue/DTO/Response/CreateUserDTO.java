package com.example.challengue.DTO.Response;

import lombok.Data;

/**
 * This class is to create an object that carries data between processes.
 * The specific process that use this class is Response when create a user
 * @author Jonnathan Campoberde
 * @version 1
 */

@Data
public class CreateUserDTO {

    private String username;

    private String password;
}
