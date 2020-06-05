package com.healthyfoody.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class SharedMapperTest {
	
	@Test
	void test() {
		SharedMapper mapper = new SharedMapper();
		UUID id = UUID.randomUUID(); 
		assertNotNull(mapper.map(id));
	}
}
