package com.healthyfoody.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UUIDStringValidator implements ConstraintValidator<ValidUUID, String> {

	private final String regex = "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}";

	@Override
	public void initialize(ValidUUID constraintAnnotation) {
		// No values to initialize
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value.matches(this.regex);
	}

}
