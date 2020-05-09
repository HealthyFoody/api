package com.healthyfoody.service.condition;

import com.healthyfoody.entity.Combo;
import com.healthyfoody.entity.Meal;
import com.healthyfoody.repository.jpa.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

public class ComboContainsMealValidator implements ConditionValidator {

    Combo combo;
    Meal meal;

    public ComboContainsMealValidator(Combo combo, Meal meal) {
        this.combo = combo;
        this.meal = meal;
    }

    @Override
    public Optional<ValidationError> validate() {
        if (combo.getMealGroups().stream().anyMatch(x -> x.getMeals().contains(meal)))
            return Optional.empty();
        return Optional.of(new ValidationError(String.format("%s no contiene a %s",combo.getName(), meal.getName()),
                combo.getId()));
    }
}
