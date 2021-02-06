package com.freenow.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class RestExceptionHandling extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorList = new ArrayList<>();
        BindingResult bindingResult = ex.getBindingResult();
        for( ObjectError objectError : bindingResult.getAllErrors()){
            errorList.add(objectError.getDefaultMessage());
        }
        log.info(" Error Parsing JSON Request body -> Error :"+ errorList);
        return new ResponseEntity<>(errorList,HttpStatus.BAD_REQUEST);
    }

    /*
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<String> errorList = new ArrayList<>();
       BindingResult bindingResult = ex.getBindingResult();
       for( ObjectError objectError : bindingResult.getAllErrors()){
            errorList.add(objectError.getDefaultMessage());
       }
       log.info(" Error Parsing JSON Request body -> Error :"+ errorList);
        return new ResponseEntity<>(errorList,HttpStatus.BAD_REQUEST);
    }*/

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

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintsViolationException(ConstraintViolationException ex){
        String errMsg = ex.getMessage();
        log.info("Handling Constraint Violation Exception");
        return new ResponseEntity<>(errMsg,HttpStatus.NOT_FOUND);
    }

/*

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Object o = new Object();
        String errMsg = ex.getMessage();
        log.info("Handling Constraint Violation Exception");
        return new ResponseEntity<>(o,HttpStatus.CREATED);
    }
*/
}
