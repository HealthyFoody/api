package com.healthyfoody.api.common;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

public class UUIDStringValidator {
    public class UUIDValidator implements ConstraintValidator<ValidUUID, String> {

        private final String regex = "[0-9a-fA-F]{8}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{4}\\-[0-9a-fA-F]{12}";

        @Override
        public void initialize(ValidUUID constraintAnnotation) {

        }

        @Override
        public boolean isValid(String value, ConstraintValidatorContext context) {
            return value.matches(this.regex);
        }

    }
}
