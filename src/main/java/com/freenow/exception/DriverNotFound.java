package com.freenow.exception;

public class DriverNotFound extends EntityNotFoundException{
    public DriverNotFound(String message) {
        super(message);
    }
}
