package com.healthyfoody.service.impl;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthyfoody.dto.response.CustomerResponse;
import com.healthyfoody.entity.Customer;
import com.healthyfoody.entity.UserAccount;
import com.healthyfoody.exception.ResourceNotFoundException;
import com.healthyfoody.mapper.CustomerMapper;
import com.healthyfoody.repository.jpa.CustomerRepository;
import com.healthyfoody.service.CustomerService;
import com.healthyfoody.service.UserService; 

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerMapper customerMapper;

    public void createCustomerProfile(UUID userId) {

        if (!customerRepository.existsCustomerByUserId(userId)){
            Customer customer = new Customer();

            UserAccount user = UserAccount.builder().id(userId).build();
            customer.setUser(user);

            save(customer);
        }
    }

    @Override
    public void updateActiveCart(Customer customer, UUID cartId) {
        customer.setCurrentCart(cartId);
        save(customer);
    }

    @Override
    public CustomerResponse findCustomerByUser(UUID userId) {
    	
    	Customer result = null;

    	result = customerRepository.findCustomerByUserId(userId).orElseThrow(() -> new ResourceNotFoundException(userId, "userId", Customer.class));

        return customerMapper.toResponse(result);
    }

    @Override
    public CustomerResponse findById(UUID id) throws ResourceNotFoundException {
        Customer result = findEntityById(id);
        return customerMapper.toResponse(result);
    }

    @Transactional
    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }

	@Override
	public Customer findEntityById(UUID id) throws ResourceNotFoundException {
		return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, UserAccount.class));
	}
}
