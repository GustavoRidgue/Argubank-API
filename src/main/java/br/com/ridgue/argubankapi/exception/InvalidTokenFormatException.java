package br.com.ridgue.argubankapi.exception;

import javassist.NotFoundException;

public class InvalidTokenFormatException extends NotFoundException {
    public InvalidTokenFormatException(String message) {
        super(message);
    }
}
