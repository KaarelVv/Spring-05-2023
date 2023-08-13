package com.carsite.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;


import java.util.Date;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(EmailException.class)
    public ResponseEntity<?> handleEmailException(EmailException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(NameException.class)
    public ResponseEntity<?> handleNameException(NameException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handle(NameException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidPriceFormatException.class)
    public ResponseEntity<?> handlePriceFormatException(InvalidPriceFormatException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "InvalidPriceFormatException",
                ex.getMessage(),
                new Date());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(ImageFileException.class)
    public ResponseEntity<?> handlePriceFormatException(ImageFileException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "ImageException",
                ex.getMessage(),
                new Date());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }




}
