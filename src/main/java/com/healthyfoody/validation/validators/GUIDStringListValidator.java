package com.healthyfoody.validation.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.healthyfoody.util.GUIDUtil;
import com.healthyfoody.validation.annotations.GUID;

import java.util.List;
import java.util.UUID;

public class GUIDStringListValidator implements ConstraintValidator<GUID, List<String>> {
    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {
        for (String s: value) {
            if (!GUIDUtil.validGUID(s))
                return false;
        }
        return true;
    }
}

