package com.freenow.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Car has been already booked")
public class CarAlreadyInUseException extends Exception{

    public CarAlreadyInUseException(String message){
        super(message);
    }

}
