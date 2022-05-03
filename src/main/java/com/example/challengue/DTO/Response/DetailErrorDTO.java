package com.example.challengue.DTO.Response;

import lombok.Data;

import java.util.Date;

/**
 * This class is to create an object that carries data between processes.
 * The specific process that use this class is to show details of the errors
 * @author Jonnathan Campoberde
 * @version 1
 */

@Data
public class DetailErrorDTO {

    private String message;

    private String detail;

    private Date timestamp;

    public DetailErrorDTO(Date timestamp, String message, String detail) {
        this.message = message;
        this.detail = detail;
        this.timestamp = timestamp;
    }
}
