/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.televisionblog.validation;

import java.util.ArrayList;
import java.util.List;


public class ValidationErrorContainer {
       
    private List<ValidationError> errors = new ArrayList();
      
    public void addError(String message, String fieldName) {
        errors.add(new ValidationError(fieldName, message));
    }
    
    public List<ValidationError> getErrors() {
        return errors;
    }   
    
}
