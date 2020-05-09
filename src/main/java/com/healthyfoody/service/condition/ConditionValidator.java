package com.healthyfoody.service.condition;

import java.util.Optional;

@FunctionalInterface
public interface ConditionValidator {
    Optional<ValidationError> validate();
}
