package com.tatyanayavkina.internalvalidation.processor;

import com.tatyanayavkina.model.ValidationType;
import org.springframework.stereotype.Component;

@Component
public class IsTrueValidationProcessor extends AbstractValidationProcessor implements ValidationProcessor {

    @Override
    public ValidationType getValidationType() {
        return ValidationType.IS_TRUE;
    }

    @Override
    protected Class<?> getSupportedType() {
        return Boolean.class;
    }

    @Override
    protected boolean isValidProperty(Object property) {
        Boolean prop = (Boolean) property;
        return prop;
    }
}
