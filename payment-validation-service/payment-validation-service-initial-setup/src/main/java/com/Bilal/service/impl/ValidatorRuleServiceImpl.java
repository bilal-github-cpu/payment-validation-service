package com.Bilal.service.impl;

import org.springframework.stereotype.Service;

import com.Bilal.cache.ValidationRulesCache;
import com.Bilal.service.interfaces.ValidatorRuleService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ValidatorRuleServiceImpl implements ValidatorRuleService {

	private ValidationRulesCache validationRulesCache;

	public ValidatorRuleServiceImpl(ValidationRulesCache validationRulesCache) {
		this.validationRulesCache = validationRulesCache;
	}

	@Override
	public boolean reloadValidationRulesAndParams() {
		log.info("Reloading validation rules and params");
		validationRulesCache.resetValidationRulesAndParams();
		
		log.info("Deleted validator rules and params");
		
		validationRulesCache.loadValidatorRulesAndParams();
		log.info("Loaded validator rules and params");
		return true;
	}

}
