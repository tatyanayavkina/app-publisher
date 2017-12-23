package com.tatyanayavkina.internalvalidation.processor;

import com.tatyanayavkina.exception.ObjectNotValidException;
import org.apache.commons.beanutils.PropertyUtilsBean;

public abstract class AbstractValidationProcessor implements ValidationProcessor {

    private final static PropertyUtilsBean pub = new PropertyUtilsBean();

    @Override
    public void validate(Object model, String propertyPath) {
        Object property;
        try {
            property = pub.getProperty(model, propertyPath);
        } catch (Exception e) {
            throw new RuntimeException("Unable to retrieve object property", e);
        }

        checkPropertyType(property);
        if (!isValidProperty(property)) {
            throw new ObjectNotValidException(model, propertyPath);
        }
    }

    private void checkPropertyType(Object property) {
        if (property == null || !getSupportedType().isInstance(property)) {
            throw new RuntimeException("Object property has unsupported type or is null");
        }
    }

    protected abstract Class<?> getSupportedType();

    protected abstract boolean isValidProperty(Object property);
}
