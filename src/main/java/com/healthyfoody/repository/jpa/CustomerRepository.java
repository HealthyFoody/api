package com.healthyfoody.repository.jpa;

import com.healthyfoody.entity.Customer;
import com.healthyfoody.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    Optional<Customer> findCustomerByUserId(UUID userId);

    Boolean existsCustomerByUserId(UUID userId);
}
