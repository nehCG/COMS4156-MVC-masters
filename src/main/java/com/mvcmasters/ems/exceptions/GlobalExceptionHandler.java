package com.mvcmasters.ems.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Handles exceptions across all controllers.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Converts CustomException to a unified response.
     *
     * @param e the caught CustomException
     * @return response with exception details
     */
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> handleCustomException(
            final CustomException e) {
        return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
    }
}
