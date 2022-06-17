package br.com.ridgue.argubankapi.exception;

import javassist.NotFoundException;

public class AlreadyHasAccountException extends NotFoundException {
    public AlreadyHasAccountException(String message) {
        super(message);
    }
}
