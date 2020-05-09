package com.healthyfoody.service;

import com.healthyfoody.entity.Customer;
import com.healthyfoody.entity.UserAccount;

import java.util.UUID;

public interface CustomerService extends CrudService<Customer, UUID> {

    Customer findCustomerByUser(UserAccount user);
}
