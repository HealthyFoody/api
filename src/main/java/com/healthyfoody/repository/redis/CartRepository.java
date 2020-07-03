package com.healthyfoody.repository.redis;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.healthyfoody.entity.redis.Cart;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart, UUID> {
}
