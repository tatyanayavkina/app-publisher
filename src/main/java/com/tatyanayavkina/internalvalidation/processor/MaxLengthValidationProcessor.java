package com.tatyanayavkina.internalvalidation.processor;

import com.tatyanayavkina.model.ValidationType;
import org.springframework.stereotype.Component;

@Component
public class MaxLengthValidationProcessor extends AbstractValidationProcessor implements ValidationProcessor {

    public static int MAX_LENGTH_VALUE = 15;

    @Override
    public ValidationType getValidationType() {
        return ValidationType.MAX_LENGTH;
    }

    @Override
    protected Class<?> getSupportedType() {
        return String.class;
    }

    @Override
    protected boolean isValidProperty(Object property) {
        String prop = (String) property;
        return prop.length() <= MAX_LENGTH_VALUE;
    }
}
