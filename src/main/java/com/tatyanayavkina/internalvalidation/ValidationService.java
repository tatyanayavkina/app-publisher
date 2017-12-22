package com.tatyanayavkina.internalvalidation;


public interface ValidationService<T> {

    void validate(T model);
}
