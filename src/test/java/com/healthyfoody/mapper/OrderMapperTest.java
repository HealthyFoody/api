package com.healthyfoody.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.github.javafaker.Faker;
import com.healthyfoody.dto.response.OrderResponse;
import com.healthyfoody.entity.*;


public class OrderMapperTest {

	OrderMapper mapper = new OrderMapperImpl(new SharedMapper());
	
	Faker faker = new Faker(new Locale("es-MX"));
	Random r = new Random();
	
	@Test
	void test() throws JsonProcessingException {
		
		List<OrderProduct> list = new ArrayList<>();
		Order order = Order.builder()
				.customer(new Customer())
				.courier(new Courier())
				.store(new Store())
				.orderDate(LocalDateTime.now())
				.paymentType(PaymentType.CARD_PAYMENT)
				.status(OrderStatus.CHARGED)
				.orderProducts(list)
				.build();
		
		
		assertNotNull(order.getId());
		
		int[] ints = r.ints(5,50,180).toArray();
		for (int v : ints) {
			Meal m = Meal.builder().name(faker.food().dish()).build();
			list.add(OrderProduct.builder()
					.product(m)
					.quantity(r.nextInt(3))
					.price(BigDecimal.valueOf(v*10L, 2))
					.isCombo(false)
					.build()
					);
		}
		
		
		OrderResponse response = mapper.toResponse(order);
		
		ObjectWriter writer = new ObjectMapper().writerWithDefaultPrettyPrinter();
		String json;

		json = writer.writeValueAsString(response);
		System.out.println(json);

		assertEquals(order.getId().toString(), response.getId());
	}
}
