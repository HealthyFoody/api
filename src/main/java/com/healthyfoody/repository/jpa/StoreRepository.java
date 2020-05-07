package com.healthyfoody.repository.jpa;

import com.healthyfoody.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StoreRepository extends JpaRepository<Store, UUID> {
	
}
