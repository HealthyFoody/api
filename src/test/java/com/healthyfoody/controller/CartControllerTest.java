package com.healthyfoody.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.healthyfoody.dto.request.CartItemRequest;
import com.healthyfoody.service.CartService;

@SpringBootTest
public class CartControllerTest {
	
	@Autowired
	MockMvc mvc;
	
	@MockBean
	CartService cartService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	void testAddCartItem() throws Exception {
		
		String cartId = UUID.randomUUID().toString();
		
		CartItemRequest request = new CartItemRequest(UUID.randomUUID(), 1);
		
		mvc.perform(post("/cart/{cartId}/meal",cartId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request)))
			.andExpect(status().isOk());
		
		
	}
}
