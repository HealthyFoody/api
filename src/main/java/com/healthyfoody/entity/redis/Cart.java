package com.healthyfoody.entity.redis;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.springframework.data.redis.core.RedisHash;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@RedisHash("cart")
@EqualsAndHashCode
public class Cart{

    @Id
    UUID id;

    @NotNull
    Boolean mutable;

    Set<CartMeal> meals;

    Set<CartCombo> combos;

    public Cart() {
        this.id = UUID.randomUUID();
        this.mutable = true;
        this.meals = new HashSet<>();
        this.combos = new HashSet<>();
    }
}
