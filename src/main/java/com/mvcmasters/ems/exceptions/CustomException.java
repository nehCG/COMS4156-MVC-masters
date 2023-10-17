package com.mvcmasters.ems.exceptions;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException {

    private HttpStatus statusCode;

    public CustomException(String message, HttpStatus statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public CustomException(String message, Throwable cause, HttpStatus statusCode) {
        super(message, cause);
        this.statusCode = statusCode;
    }

    // Getters and setters
    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }
}

