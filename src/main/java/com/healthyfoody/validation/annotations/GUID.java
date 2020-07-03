package com.healthyfoody.validation.annotations;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.healthyfoody.validation.validators.GUIDObjectListValidator;
import com.healthyfoody.validation.validators.GUIDObjectValidator;
import com.healthyfoody.validation.validators.GUIDStringListValidator;
import com.healthyfoody.validation.validators.GUIDStringValidator;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {
        GUIDObjectValidator.class,
        GUIDStringValidator.class,
        GUIDStringListValidator.class,
        GUIDObjectListValidator.class})
public @interface GUID {

    String message() default "must be a valid UUID";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}