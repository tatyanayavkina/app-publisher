package com.tatyanayavkina.internalvalidation.processor;

import com.tatyanayavkina.model.ValidationType;
import org.springframework.stereotype.Component;

@Component
public class MinLengthValidationProcessor extends AbstractValidationProcessor implements ValidationProcessor {

    public static int MIN_LENGTH_VALUE = 15;

    @Override
    public ValidationType getValidationType() {
        return ValidationType.MIN_LENGTH;
    }

    @Override
    protected Class<?> getSupportedType() {
        return String.class;
    }

    @Override
    protected boolean isValidProperty(Object property) {
        String prop = (String) property;
        return prop.length() >= MIN_LENGTH_VALUE;
    }
}
