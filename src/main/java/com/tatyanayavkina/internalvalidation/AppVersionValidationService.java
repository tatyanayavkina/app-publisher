package com.tatyanayavkina.internalvalidation;

import com.tatyanayavkina.model.AppVersion;
import org.springframework.stereotype.Service;

@Service
public class AppVersionValidationService implements ValidationService<AppVersion> {

    @Override
    public void validate(AppVersion model) {

    }
}
