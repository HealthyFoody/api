package com.healthyfoody;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class RestApiApplicationTests {

	@Value("${spring.profiles.active}")
	public String profile;
	
	@Test
	void contextLoads() {
		assertTrue(profile.equals("test"));
	}

}
