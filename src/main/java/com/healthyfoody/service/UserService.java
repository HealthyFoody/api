package com.healthyfoody.service;

import com.healthyfoody.entity.UserAccount;

import java.util.UUID;

public interface UserService extends CrudService<UserAccount, UUID> {
    UserAccount register(UserAccount user);
}
