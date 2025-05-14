package com.records.customer.exception;

import java.util.HashMap;

import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.records.common.exceptions.ErrorResponse;
import com.records.common.exceptions.GlobalExceptionHandler;

@RestControllerAdvice(basePackages = "com.records.customer")
@Primary
public class CustomerExceptionHandler extends GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCustomerNotFoundException(CustomerNotFoundException ex) {
        var errors = new HashMap<String, String>();
        var fieldName = "customer";
        var errorMessage = ex.getMessage();
        errors.put(fieldName, errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(errors));
    }

}
