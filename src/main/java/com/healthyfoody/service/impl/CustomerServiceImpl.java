package com.healthyfoody.service.impl;

import com.healthyfoody.entity.Customer;
import com.healthyfoody.entity.UserAccount;
import com.healthyfoody.exception.BusinessException;
import com.healthyfoody.exception.ResourceNotFoundException;
import com.healthyfoody.repository.jpa.CustomerRepository;
import com.healthyfoody.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Validator;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    @Transactional
    public Customer findCustomerByUser(UserAccount user) {
        if (!customerRepository.existsCustomerByUser(user)){
            Customer customer = new Customer();
            customer.setUser(user);

            return customerRepository.save(customer);
        }
        return null;
    }

    @Override
    public Customer findById(UUID id) throws ResourceNotFoundException {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, UserAccount.class));
    }

    @Transactional
    @Override
    public Customer insert(Customer customer){
        return customerRepository.save(customer);
    }
}
