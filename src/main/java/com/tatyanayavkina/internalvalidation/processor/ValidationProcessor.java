package com.tatyanayavkina.internalvalidation.processor;

import com.tatyanayavkina.model.ValidationType;

public interface ValidationProcessor {

    ValidationType getValidationType();

    void validate(Object model, String propertyPath);
}
