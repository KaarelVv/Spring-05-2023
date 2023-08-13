package com.carsite.exception;

public class InvalidPriceFormatException extends RuntimeException{
    public InvalidPriceFormatException(String message) {
        super(message);
    }
}
