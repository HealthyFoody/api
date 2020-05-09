package com.healthyfoody.validation.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.healthyfoody.util.UuidUtil;
import com.healthyfoody.validation.annotations.ValidUUID;

public class UUIDStringValidator implements ConstraintValidator<ValidUUID, String> {

	private final String regex = UuidUtil.PATTERN;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.matches(this.regex);
	}

}
