package com.example.challengue.Exception;


import com.example.challengue.DTO.Response.DetailErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is to handler the exceptions in the application
 * @author Jonnathan Campoberde
 * @version 1
 */

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<DetailErrorDTO> manageResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
        DetailErrorDTO errorDetail = new DetailErrorDTO(new Date(),exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ControlVaccinesException.class)
    public ResponseEntity<DetailErrorDTO> manageUserVaccinesAppException(ControlVaccinesException exception, WebRequest webRequest){
        DetailErrorDTO errorDetails = new DetailErrorDTO(new Date(),exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<DetailErrorDTO> manageGlobalException(Exception exception, WebRequest webRequest){
        DetailErrorDTO errorDetails = new DetailErrorDTO(new Date(),exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError)error).getField();
            String message = error.getDefaultMessage();

            errores.put(fieldName, message);
        });

        return new ResponseEntity<>(errores,HttpStatus.BAD_REQUEST);
    }
}
