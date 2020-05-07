package com.healthyfoody.repository.jpa;

import com.healthyfoody.entity.Order;
import com.healthyfoody.entity.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

    Page<Order> findAllByCustomer_Id(UUID customerId, Pageable pageable);

    //@Query("select count(o.id) from Order o where o.customer.id = :customerId and o.status = :status")
    Integer countOrdersByCustomerIdAndStatus(UUID customerId, OrderStatus status);
}
