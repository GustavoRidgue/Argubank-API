package br.com.ridgue.argubankapi.exception;

import javassist.NotFoundException;

public class ResourceNotFoundException extends NotFoundException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
