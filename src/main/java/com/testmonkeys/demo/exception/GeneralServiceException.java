package com.testmonkeys.demo.exception;

import org.springframework.http.HttpStatus;

public class GeneralServiceException extends RuntimeException {
    private final String message;

    public GeneralServiceException() {
        this.message = "Internal server error";
    }

    public GeneralServiceException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public GeneralServiceException(String message) {
        super(message);
        this.message = message;
    }

    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
