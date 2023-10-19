package com.mvcmasters.ems.exceptions;

import org.springframework.http.HttpStatus;

/**
 * Custom exception class that includes an HTTP status code.
 */
public class CustomException extends RuntimeException {
    /**
     * The HTTP status code associated with this exception.
     */
    private HttpStatus statusCode;
    /**
     * Constructor with a message and status code.
     *
     * @param message the detail message.
     * @param code the HTTP status code.
     */
    public CustomException(final String message, final HttpStatus code) {
        super(message);
        this.statusCode = code;
    }
    /**
     * Constructor with a message, cause, and status code.
     *
     * @param message the detail message.
     * @param cause the cause of the exception.
     * @param code the HTTP status code.
     */
    public CustomException(final String message,
            final Throwable cause, final HttpStatus code) {
        super(message, cause);
        this.statusCode = code;
    }

    /**
     * Retrieves the HTTP status code associated with this exception.
     *
     * @return the HTTP status code.
     */
    public HttpStatus getStatusCode() {
        return statusCode;
    }

    /**
     * Updates the HTTP status code for this exception.
     *
     * @param code the new HTTP status code; not null.
     */
    public void setStatusCode(final HttpStatus code) {
        this.statusCode = code;
    }
}

