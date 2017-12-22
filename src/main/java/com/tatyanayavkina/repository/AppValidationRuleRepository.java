package com.tatyanayavkina.repository;

import com.tatyanayavkina.model.AppValidationRule;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AppValidationRuleRepository extends CrudRepository<AppValidationRule, Long> {

    List<AppValidationRule> findAll();
}
