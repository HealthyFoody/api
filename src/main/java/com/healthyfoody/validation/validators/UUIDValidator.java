package com.healthyfoody.validation.validators;

import java.util.UUID;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.healthyfoody.util.UuidUtil;
import com.healthyfoody.validation.annotations.ValidUUID;

public class UUIDValidator implements ConstraintValidator<ValidUUID, UUID> {

	private final String regex = UuidUtil.PATTERN;

	@Override
	public boolean isValid(UUID value, ConstraintValidatorContext context) {
		return value.toString().matches(this.regex);
	}

}
