package br.com.ridgue.argubankapi.exception;

import javassist.NotFoundException;

public class TokenNotAuthenticatedException extends NotFoundException {
    public TokenNotAuthenticatedException(String message) {
        super(message);
    }
}
