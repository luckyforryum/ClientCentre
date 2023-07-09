package com.kata.clientprofileupdate.exception;

import org.kata.erroresponse.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import java.io.IOException;

@ControllerAdvice
public class RestTemplateExceptionHandler {



    @ExceptionHandler(ResourceAccessException.class)
    public ResponseEntity<ErrorResponse> handleResourceAccessException(IOException ex) {
        System.out.println(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse("The requested resource is currently unavailable11111111", HttpStatus.SERVICE_UNAVAILABLE, 503);
        return new ResponseEntity<>(errorResponse, HttpStatus.SERVICE_UNAVAILABLE);
    }



}
