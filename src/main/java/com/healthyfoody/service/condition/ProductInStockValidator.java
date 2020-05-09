package com.healthyfoody.service.condition;

import com.healthyfoody.entity.Product;
import com.healthyfoody.entity.Store;
import com.healthyfoody.repository.jpa.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

public class ProductInStockValidator implements ConditionValidator {

    ProductRepository productRepository;

    Product product;
    Store store;

    public ProductInStockValidator(Product product, Store store) {
        this.product = product;
        this.store = store;
    }

    @Override
    public Optional<ValidationError> validate() {
        if (productRepository.isThereStockAtStore(product.getId(), store.getId()))
            return Optional.empty();
        return Optional.of(new ValidationError(
                String.format("%s se encuentra agotado en %s", product.getName(), store.getDescription()),
                product.getId()));
    }
}
