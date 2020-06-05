package com.healthyfoody.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class SharedMapper {

	public String map(UUID id) {
		if (id == null)
			return null;
		return id.toString();
	}
}
