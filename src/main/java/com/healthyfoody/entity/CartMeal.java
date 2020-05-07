package com.healthyfoody.entity;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CartMeal {

    @NonNull
    @EqualsAndHashCode.Include
    UUID productId;

    Integer quantity;
}
