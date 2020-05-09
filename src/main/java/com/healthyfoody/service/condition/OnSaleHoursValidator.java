package com.healthyfoody.service.condition;

import com.healthyfoody.entity.Product;
import com.healthyfoody.repository.jpa.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Optional;

public class OnSaleHoursValidator implements ConditionValidator {

    ProductRepository productRepository;

    Product product;
    LocalTime hour;

    public OnSaleHoursValidator(Product product, LocalTime hour) {
        this.product = product;
        this.hour = hour;
    }

    @Override
    public Optional<ValidationError> validate() {
        if (productRepository.isOnSaleSpan(product.getId(), hour))
            return Optional.empty();
        return Optional.of(new ValidationError(
                String.format("%s se se encuentra disponible a las %s", product.getName(), hour),
                product.getId()));
    }
}
