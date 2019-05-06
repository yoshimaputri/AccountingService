package com.service.accounting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InputFormatException extends RuntimeException {
    public InputFormatException() {
        super("Input doesn't match our requirement.");
    }

    public InputFormatException(String s) {
        super(s);
    }
}
