package com.ceiba.biblioteca.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidLoanException extends RuntimeException{
    public InvalidLoanException(String message){
        super(message);
    }
}
