package com.healthyfoody.validation;

import java.util.UUID;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UUIDValidator implements ConstraintValidator<ValidUUID, UUID> {

	private final String regex = "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}";

	@Override
	public void initialize(ValidUUID constraintAnnotation) {
		// No values to initialize
	}

	@Override
	public boolean isValid(UUID value, ConstraintValidatorContext context) {
		return value.toString().matches(this.regex);
	}

}
