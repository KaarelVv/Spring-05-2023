package com.carsite.exception;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ErrorResponse {

    private int status;
    private String error;
    private String message;
    private Date timestamp;

    public ErrorResponse(int status, String error, String message, Date timestamp) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.timestamp = timestamp;
    }
}
