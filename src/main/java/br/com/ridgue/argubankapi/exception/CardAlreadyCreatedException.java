package br.com.ridgue.argubankapi.exception;

import javassist.NotFoundException;

public class CardAlreadyCreatedException extends NotFoundException {
    public CardAlreadyCreatedException(String message) {
        super(message);
    }
}
