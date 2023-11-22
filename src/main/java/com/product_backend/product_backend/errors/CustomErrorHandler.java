package com.product_backend.product_backend.errors;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

@ControllerAdvice
public class CustomErrorHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public void handleIllegalArgumentException(
            IllegalArgumentException exception,
            ServletWebRequest webRequest)
            throws IOException {

        System.out.print(exception.getMessage());
        exception.printStackTrace();

        webRequest.getResponse().sendError(HttpStatus.BAD_REQUEST.value());
    }
}
