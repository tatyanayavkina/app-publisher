package com.tatyanayavkina.internalvalidation;

import com.tatyanayavkina.internalvalidation.processor.ValidationProcessor;
import com.tatyanayavkina.model.AppValidationRule;
import com.tatyanayavkina.model.AppVersion;
import com.tatyanayavkina.model.ValidationType;
import com.tatyanayavkina.repository.AppValidationRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class AppVersionValidationService implements ValidationService<AppVersion> {

    private final AppValidationRuleRepository appValidationRuleRepository;

    private final Map<ValidationType, ValidationProcessor> processorsMap;

    @Autowired
    public AppVersionValidationService(AppValidationRuleRepository appValidationRuleRepository,
                                       List<ValidationProcessor> processors) {
        if (processors.size() < ValidationType.values().length) {
            throw new RuntimeException("You should define ValidationProcessors for all ValidationTypes");
        }

        this.appValidationRuleRepository = appValidationRuleRepository;
        this.processorsMap = processors.stream()
                .collect(Collectors.toMap(ValidationProcessor::getValidationType, Function.identity()));
    }

    @Override
    public void validate(AppVersion model) {
        List<AppValidationRule> rules = appValidationRuleRepository.findAll();

        for (AppValidationRule rule: rules) {
            ValidationProcessor processor = processorsMap.get(rule.getType());
            processor.validate(model, rule.getPath());
        }
    }
}
