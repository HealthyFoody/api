package com.healthyfoody.mapper;

import java.lang.annotation.*;

import org.mapstruct.Qualifier;

@Qualifier
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface EntityIdResolver {

}
