package com.tatyanayavkina.api.v1.exception;

public class EntityNotFoundException extends RuntimeException {

    private final String entity;

    private final long id;

    public EntityNotFoundException(String entity, long id) {
        super();
        this.entity = entity;
        this.id = id;
    }

    @Override
    public String getMessage() {
        return String.format("Entity %s with id=%d not found", entity, id);
    }
}
