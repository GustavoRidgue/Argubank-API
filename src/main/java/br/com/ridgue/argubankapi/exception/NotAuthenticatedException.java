package br.com.ridgue.argubankapi.exception;

import javassist.NotFoundException;

public class NotAuthenticatedException extends NotFoundException {
    public NotAuthenticatedException(String message) {
        super(message);
    }
}
