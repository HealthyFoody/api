package com.healthyfoody.validation.validators;

import com.healthyfoody.util.GUIDUtil;
import com.healthyfoody.validation.annotations.GUID;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.UUID;

public class GUIDObjectListValidator implements ConstraintValidator<GUID, List<UUID>> {
    @Override
    public boolean isValid(List<UUID> value, ConstraintValidatorContext context) {
        for (UUID s: value) {
            if (!GUIDUtil.validGUID(s)) {
                return false;
            }
        }
        return true;
    }
}
