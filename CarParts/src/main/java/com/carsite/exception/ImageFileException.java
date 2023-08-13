package com.carsite.exception;

import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

public class ImageFileException extends RuntimeException {
    public ImageFileException(String message) {
        super(message);
    }
}
