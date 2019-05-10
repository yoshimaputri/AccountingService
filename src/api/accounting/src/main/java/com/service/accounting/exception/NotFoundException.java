package com.service.accounting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("The requested resource cannot be found.");
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String resource, Object value) {
        super("Resource '" + resource + "' with value '" + value + "' cannot be found.");
    }
}
