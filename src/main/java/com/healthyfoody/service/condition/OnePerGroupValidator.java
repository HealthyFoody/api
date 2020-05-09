package com.healthyfoody.service.condition;

import com.healthyfoody.entity.Combo;
import com.healthyfoody.entity.Meal;
import com.healthyfoody.entity.MealGroup;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;


public class OnePerGroupValidator implements ConditionValidator {

    Combo combo;
    List<Meal> meals;

    public OnePerGroupValidator(Combo combo, List<Meal> meals) {
        this.combo = combo;
        this.meals = meals;
    }

    @Override
    public Optional<ValidationError> validate() {

        List<String> errors = new ArrayList<>();

        for (MealGroup mg : combo.getMealGroups()) {
            List<String> mealsInSameGroup = mg.getMeals().stream().filter(meals::contains).map(Meal::getName).collect(Collectors.toList());

            boolean isOptional = mg.getOptional();
            long matches = mealsInSameGroup.size();

            if (matches > 1)
                errors.add("Más de un plato pertencen al grupo " + mg.getMeals() + ": " + String.join(", ",mealsInSameGroup));
            else if (!isOptional && matches < 1)
                errors.add("Es necesario seleccionar un plato del grupo " + mg.getMeals());
        }
        if (errors.isEmpty())
            return Optional.empty();

        Map<String, Object> detail = new HashMap<>();
        detail.put("combo", combo.getId());
        detail.put("errors", errors);
        return Optional.of(new ValidationError(String.format("La combinacion de combo %s no es válida", combo.getName()),detail));
    }
}
