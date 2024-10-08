package com.user.tracking.imperative.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ServerExceptionHandler {

    private static final String PUBLISHER_ID_AND_USER_ID_CANNOT_BE_NULL = "PublisherId and userId cannot be null.";

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<StandardError> illegalClientInput(BusinessException exc, HttpServletRequest req) {
        HttpStatus status = HttpStatus.PRECONDITION_FAILED;
        StandardError standardError = new StandardError(
                Instant.now(), status.value(), PUBLISHER_ID_AND_USER_ID_CANNOT_BE_NULL, exc.getMessage(), req.getRequestURI()
        );
        return ResponseEntity.status(status).body(standardError);
    }
}
