package br.com.ridgue.argubankapi.exception;

import javassist.NotFoundException;

public class NotOldEnoughException extends NotFoundException {
    public NotOldEnoughException(String message) {
        super(message);
    }
}
