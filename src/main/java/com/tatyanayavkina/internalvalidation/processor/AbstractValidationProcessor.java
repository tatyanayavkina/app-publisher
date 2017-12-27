package com.tatyanayavkina.internalvalidation.processor;

import com.tatyanayavkina.exception.ObjectNotValidException;
import org.apache.commons.beanutils.PropertyUtilsBean;

public abstract class AbstractValidationProcessor implements ValidationProcessor {
    // @see https://stackoverflow.com/questions/12999214/get-the-value-of-a-field-given-the-hierarchical-path
    private final static PropertyUtilsBean pub = new PropertyUtilsBean();

    @Override
    public void validate(Object model, String propertyPath) {
        Object property;
        try {
            property = pub.getProperty(model, propertyPath);
        } catch (Exception e) {
            throw new IllegalArgumentException("Unable to retrieve object property", e);
        }

        checkPropertyIsNotNull(property);
        if (!isValidProperty(property)) {
            throw new ObjectNotValidException(model);
        }
    }

    private void checkPropertyIsNotNull(Object property) {
        if (property == null) {
            throw new RuntimeException("Object property is null");
        }
    }

    protected abstract Class<?> getSupportedType();

    protected abstract boolean isValidProperty(Object property);
}
