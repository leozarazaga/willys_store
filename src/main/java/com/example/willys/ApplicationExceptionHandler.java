package com.example.willys;

import com.example.willys.exception.*;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomerDoesNotExistException.class)
    public ResponseEntity<Object> handleCustomerDoesNotExistException(CustomerDoesNotExistException ex) {
        ErrorResponse error = new ErrorResponse(Arrays.asList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(Arrays.asList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerNameNotFoundException.class)
    public ResponseEntity<Object> handleCustomerNameNotFoundException(CustomerNameNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(Arrays.asList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleProductNotFoundException(ProductNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(Arrays.asList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSearchResultFoundException.class)
    public ResponseEntity<Object> handleProductNameNotFoundException(NoSearchResultFoundException ex) {
        ErrorResponse error = new ErrorResponse(Arrays.asList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<Object> handleOrderNotFoundException(NoSearchResultFoundException ex) {
        ErrorResponse error = new ErrorResponse(Arrays.asList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderAlreadyExistsException.class)
    public ResponseEntity<Object> handleOrderAlreadyExistsException(NoSearchResultFoundException ex) {
        ErrorResponse error = new ErrorResponse(Arrays.asList(ex.getLocalizedMessage()));
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }




    //@NotBlank
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex) {
        List<String> errors = new ArrayList<>();
        ex.getConstraintViolations().forEach(violation -> errors.add(violation.getMessage()));
        return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
    }


}
