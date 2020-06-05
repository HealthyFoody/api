package com.healthyfoody.validation.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.healthyfoody.util.GUIDUtil;
import com.healthyfoody.validation.annotations.GUID;

public class GUIDStringValidator implements ConstraintValidator<GUID, String> {
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.matches(GUIDUtil.PATTERN);
	}
}
