package com.example.challengue.Exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


/**
 * This class is to create a object with info about the resources not foud.
 * @author Jonnathan Campoberde
 * @version 1
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND )
@Data
public class ResourceNotFoundException extends RuntimeException{

    public static final Integer serialVersionUID=1;

    private String resourceName;

    private String fieldName;

    private Integer fieldValue;

    private String fieldValueString;

    public ResourceNotFoundException(String resourceName, String fieldName, Integer fieldValue){
        super(String.format("%s not found: %s : '%s'", resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValueString){
        super(String.format("%s not found: %s : '%s'", resourceName,fieldName,fieldValueString));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValueString = fieldValueString;
    }





}
