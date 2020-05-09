package com.healthyfoody.service;

import com.healthyfoody.entity.UserAccount;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.UUID;

public interface UserService extends CrudService<UserAccount, UUID>, UserDetailsService {
    UserAccount register(UserAccount user);

    UserAccount findByEmail(String email);

    Boolean emailExists(String email);
}
