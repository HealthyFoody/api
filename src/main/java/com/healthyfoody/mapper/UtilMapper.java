package com.healthyfoody.mapper;

import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UtilMapper {

    String toString(UUID uuid);
}
