package com.tatyanayavkina.exception;

public class ObjectNotValidException extends RuntimeException {

    private final Object model;

    private final String propertyPath;

    public ObjectNotValidException(Object model, String propertyPath) {
        super();
        this.model = model;
        this.propertyPath = propertyPath;
    }

    @Override
    public String getMessage() {
        return String.format("Invalid property path %s for model %s", propertyPath, model.toString());
    }
}
