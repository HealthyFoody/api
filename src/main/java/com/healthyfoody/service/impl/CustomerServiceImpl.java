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
    UserService UserRepository;
    
    @Autowired
    CustomerMapper customerMapper;

    @Override
    public CustomerResponse findCustomerByUser(UUID userId) {
    	
    	Customer result = null;
    	
    	UserAccount user = UserAccount.builder().id(userId).build();
    	
    	//TODO: verify user Exists
        if (!customerRepository.existsCustomerByUser(user)){
            Customer customer = new Customer();
            customer.setUser(user);

            result = save(customer);
        }
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
