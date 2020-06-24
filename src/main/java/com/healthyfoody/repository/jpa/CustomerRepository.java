package com.healthyfoody.repository.jpa;

import com.healthyfoody.entity.Customer;
import com.healthyfoody.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    Optional<Customer> findCustomerByUserId(UUID userId);

    Boolean existsCustomerByUserId(UUID userId);
}
