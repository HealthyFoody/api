package com.healthyfoody.validation.annotations;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.healthyfoody.validation.validators.GUIDValidator;
import com.healthyfoody.validation.validators.GUIDStringValidator;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { GUIDValidator.class, GUIDStringValidator.class })
public @interface GUID {

    String message() default "Not a valid UUID";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}