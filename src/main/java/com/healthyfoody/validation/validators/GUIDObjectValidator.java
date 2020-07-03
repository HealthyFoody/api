package com.healthyfoody.validation.validators;

import java.util.UUID;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.healthyfoody.util.GUIDUtil;
import com.healthyfoody.validation.annotations.GUID;

public class GUIDObjectValidator implements ConstraintValidator<GUID, UUID> {
	@Override
	public boolean isValid(UUID value, ConstraintValidatorContext context) {
		if (value == null) {
			return true;
		}
		return GUIDUtil.validGUID(value);
	}
}
