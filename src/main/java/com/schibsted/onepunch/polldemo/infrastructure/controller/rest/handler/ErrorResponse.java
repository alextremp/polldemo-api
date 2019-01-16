package com.schibsted.onepunch.polldemo.infrastructure.controller.rest.handler;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorResponse {

    private final LocalDateTime timestamp;
    private final Integer status;
    private final String error;
    private final String message;
    private final String cause;

    public ErrorResponse(Exception error, HttpStatus httpStatus) {
        this.timestamp = LocalDateTime.now();
        this.status = httpStatus.value();
        this.error = httpStatus.getReasonPhrase();
        this.message = error.getLocalizedMessage();
        this.cause = error.getClass().getName();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getCause() {
        return cause;
    }
}
