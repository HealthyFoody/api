package com.healthyfoody.service.impl;

import com.healthyfoody.entity.UserAccount;
import com.healthyfoody.exception.ResourceNotFoundException;
import com.healthyfoody.repository.jpa.UserRepository;
import com.healthyfoody.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserAccount register(UserAccount user) {
        // TODO: implement security
        return null;
    }

    @Override
    public UserAccount findById(UUID id) throws ResourceNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id, UserAccount.class));
    }
}
