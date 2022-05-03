package com.example.challengue.DTO.Response;

import lombok.Data;

import java.util.Date;


/**
 * This class is to create an object that carries data between processes.
 * The user's not vaccinated
 * @author Jonnathan Campoberde
 * @version 1
 */
@Data
public class UserNotVaccinetDTO {

    public String id;

    public String names;

    public String surnames;

    public String email;

    public String userName;

    private Date birthDate;

    private String address;

    private String telephone;





}
