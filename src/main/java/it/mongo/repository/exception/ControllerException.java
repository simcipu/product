/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.mongo.repository.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author simonecipullo
 */
@ControllerAdvice
public class ControllerException {
    
    
     @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorMessage> handleException(Exception ex)
    {
        ErrorMessage errorMessage = new ErrorMessage();
        if(ex.getMessage()!=null) {
            errorMessage.getErrors().add(ex.getMessage());
        }else{
            errorMessage.getErrors().add(""+ex);
        }

        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
