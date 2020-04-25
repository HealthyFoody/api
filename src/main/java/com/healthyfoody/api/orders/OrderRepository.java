package com.healthyfoody.api.orders;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

interface OrderRepository extends JpaRepository<Order, UUID> {

    Page<Order> findAllByCustomer_Id(UUID customerId, Pageable pageable);
}
