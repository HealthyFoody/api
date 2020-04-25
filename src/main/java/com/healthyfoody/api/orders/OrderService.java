package com.healthyfoody.api.orders;

import com.healthyfoody.api.customers.Customer;
import com.healthyfoody.api.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public UUID placeOrder(Order order) {

         Order placedOrder = orderRepository.save(order);

         return placedOrder.getId();
    }

    public Boolean editOrder(Order order) {

        return orderRepository.save(order) != null ? true : false;
    }

    public Page<Order> findOrder(UUID customerId ,int page) {

        Pageable pageable = PageRequest.of(page,10, Sort.by(Sort.Direction.DESC, "orderDate"));

        return orderRepository.findAllByCustomer_Id(customerId, pageable);
    }

    public Optional<Order> findOrderById(UUID id) {

        return orderRepository.findById(id);
    }
}
