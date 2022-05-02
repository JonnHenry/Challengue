package com.example.challengue.Exception;

import lombok.Data;
import org.springframework.http.HttpStatus;


/**
 * This class is to create a object with the HttpSatatus and the message of exception
 * @author Jonnathan Campoberde
 * @version 1
 */

@Data
public class ControlVaccinesException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private HttpStatus status;
    private String message;

    public ControlVaccinesException(HttpStatus status, String message) {
        super();
        this.status = status;
        this.message = message;
    }
}
