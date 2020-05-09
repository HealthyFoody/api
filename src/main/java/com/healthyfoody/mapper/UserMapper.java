package com.healthyfoody.mapper;

import com.healthyfoody.dto.UserRequest;
import com.healthyfoody.dto.UserResponse;
import com.healthyfoody.entity.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { UtilMapper.class })
public interface UserMapper {

    @Mapping(target = "password", constant = "*****")
    UserResponse entityToResponse(UserAccount user);


    UserAccount requestToEntity(UserRequest dto);
}
