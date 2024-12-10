package com.Bilal.dao;

import java.util.List;
import java.util.Map;

public interface ValidationRuleDAO {
	List<String> loadActiveValidatorNames();

	Map<String, String> loadValidatorRuleParams(String validatorRuleName);
}

