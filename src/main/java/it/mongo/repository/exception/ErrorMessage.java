/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.mongo.repository.exception;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 *
 * @author simonecipullo
 */
public class ErrorMessage {
    
       private List<String> errors;

    public ErrorMessage()
    {
    }

    public ErrorMessage(List<String> errors)
    {
        this.errors = errors;
    }

    public ErrorMessage(String error)
    {
        this(Collections.singletonList(error));
    }

    public ErrorMessage(String... errors)
    {
        this(Arrays.asList(errors));
    }

    public List<String> getErrors()
    {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        return errors;
    }

    public void setErrors(List<String> errors)
    {
        this.errors = errors;
    }
    
}
