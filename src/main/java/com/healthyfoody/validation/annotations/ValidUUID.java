package com.healthyfoody.validation.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.healthyfoody.validation.validators.UUIDStringValidator;
import com.healthyfoody.validation.validators.UUIDValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { UUIDValidator.class, UUIDStringValidator.class })
public @interface ValidUUID {

    String message() default "Not a valid UUID";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}