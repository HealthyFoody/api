package com.healthyfoody.mapper;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.MapperConfig;

@MapperConfig(
		componentModel = "spring", 
		injectionStrategy = InjectionStrategy.CONSTRUCTOR,
		uses = { SharedMapper.class }
)
interface SharedMapperConfig {
}
