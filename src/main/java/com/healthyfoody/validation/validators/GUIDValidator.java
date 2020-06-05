package com.healthyfoody.validation.validators;

import java.util.UUID;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.healthyfoody.util.GUIDUtil;
import com.healthyfoody.validation.annotations.GUID;

public class GUIDValidator implements ConstraintValidator<GUID, UUID> {
	@Override
	public boolean isValid(UUID value, ConstraintValidatorContext context) {
		return value.toString().matches(GUIDUtil.PATTERN);
	}
}
