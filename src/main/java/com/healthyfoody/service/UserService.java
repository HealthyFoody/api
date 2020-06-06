package com.healthyfoody.service;

import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.healthyfoody.dto.request.UserRequest;
import com.healthyfoody.entity.UserAccount;

public interface UserService extends UserDetailsService {
	UUID register(UserRequest user);

    Boolean emailExists(String email);
    
    UserAccount findEntityByEmail(String email);
}
