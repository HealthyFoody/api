package com.healthyfoody.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@RedisHash("cart")
public class Cart{

    @Id
    UUID id;

    @NotNull
    Boolean mutable = true;

    Set<CartMeal> meals;

    Set<CartCombo> combos;

    public Cart() {
        this.id = UUID.randomUUID();
    }
}
