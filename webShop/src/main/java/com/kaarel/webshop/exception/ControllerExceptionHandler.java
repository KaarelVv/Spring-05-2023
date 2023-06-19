package com.kaarel.webshop.exception;

import com.google.common.util.concurrent.UncheckedExecutionException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Date;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ControllerExceptionHandler {

    //httpClientErrorException --> Päring ei öäinud l'bi mida teeme teise rakendusse
    @ExceptionHandler
    public ResponseEntity<ExceptionMessage> handleException(HttpClientErrorException e) {

        ExceptionMessage message = new ExceptionMessage();
        message.setMessage("Request to other app didnt go through! " /*+ e.getStatusCode()*/ + "Reason" + e.getMessage());
        message.setTimestamp(new Date());
        message.setStatus(500);

        return ResponseEntity.status(500).body(message);
    }
    @ExceptionHandler
    public ResponseEntity<ExceptionMessage> handleException(UncheckedExecutionException e) {

        ExceptionMessage message = new ExceptionMessage();
        message.setMessage("No such product!");
        message.setTimestamp(new Date());
        message.setStatus(500);

        return ResponseEntity.status(500).body(message);
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ExceptionMessage> handleException() {

        ExceptionMessage message = new ExceptionMessage();
        message.setMessage("No such element!");
        message.setTimestamp(new Date());
        message.setStatus(500);
        return ResponseEntity.status(500).body(message);
    }
    @ExceptionHandler
    public ResponseEntity<ExceptionMessage> handleException(Exception e) {

        ExceptionMessage message = new ExceptionMessage();
        message.setMessage("No such element!");
        message.setTimestamp(new Date());
        message.setStatus(400);
        return ResponseEntity.status(400).body(message);
    }
}
