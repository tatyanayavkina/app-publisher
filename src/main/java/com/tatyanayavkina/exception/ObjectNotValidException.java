package com.tatyanayavkina.exception;

public class ObjectNotValidException extends RuntimeException {

    private final Object model;

    public ObjectNotValidException(Object model) {
        super();
        this.model = model;
    }

    @Override
    public String getMessage() {
        return String.format("Model is not valid: %s", model.toString());
    }
}
