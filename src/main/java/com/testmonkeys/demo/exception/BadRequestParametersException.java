package com.testmonkeys.demo.exception;

import org.springframework.http.HttpStatus;

public class BadRequestParametersException extends GeneralServiceException {
    private static final String MESSAGE = "Bad request params";

    public BadRequestParametersException() {
        super(MESSAGE);
    }

    public BadRequestParametersException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestParametersException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
