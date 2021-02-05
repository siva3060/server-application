package com.freenow.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class RestExceptionHandling extends ResponseEntityExceptionHandler {


    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<String> handleCarNotFoundException(CarNotFoundException e){
            String errMsg = e.getMessage();
            log.info("Car Not Found Exception is handled ");
            return new ResponseEntity<>(errMsg,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DriverNotFound.class)
    public ResponseEntity<String> handleDriverNotFoundException(DriverNotFound e){
        String errMsg = e.getMessage();
        log.info("Driver Not Found Exception is handled ");
        return new ResponseEntity<>(errMsg,HttpStatus.NOT_FOUND);
    }
}
