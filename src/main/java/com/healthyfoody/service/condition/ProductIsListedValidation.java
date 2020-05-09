package com.healthyfoody.service.condition;

import com.healthyfoody.entity.Product;

import java.util.Optional;

public class ProductIsListedValidation implements ConditionValidator {

    Product product;

    public ProductIsListedValidation(Product product) {
        this.product = product;
    }

    @Override
    public Optional<ValidationError> validate() {
        if (product.getListed())
            return Optional.empty();
        return Optional.of(new ValidationError(String.format("El producto %s no está la venta", product.getName()),product.getId()));
    }
}
