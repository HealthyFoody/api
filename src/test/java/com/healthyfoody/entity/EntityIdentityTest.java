package com.healthyfoody.entity;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Objects;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class EntityIdentityTest {
	
	@AfterEach
	void divider() {
		System.out.println("---------------------------------------------");
	}
	
	@Test
	void testSameId() {
		System.out.println("ENTITIES WITH SAME IDS");
		
        Address address1 = new Address();
        address1.setId(UUID.fromString("622da2a8-85ea-11ea-bc55-0242ac130003"));
        address1.setName("Address1");
        printHash(address1);
        
        Address address2 = new Address();
        address2.setId(UUID.fromString("622da2a8-85ea-11ea-bc55-0242ac130003"));
        address2.setName("Address2");
        printHash(address2);
        
        assertTrue(address1.equals(address2));
	}
	
	@Test
	void testDifferentId() {
		System.out.println("ENTITIES WITH DIFFERENT IDS ID");
		
        Address address1 = new Address();
        address1.setId(UUID.fromString("622da2a8-85ea-11ea-bc55-0242ac130003"));
        printHash(address1);
        
        Address address2 = new Address();
        address2.setId(UUID.fromString("07b646d8-0ecb-46ca-bca9-7107865788d0"));
        printHash(address2);
        
        assertFalse(address1.equals(address2));
	}
	
	@Test
	void testAssignRandomId() {
		System.out.println("ENTITY SHOULDN'T HAVE NULL ID");
		
		Address address = new Address();
		System.out.println(address.getId());
		assertNotNull(address.getId());
	}
	
	@Test
	void testSameIdOnDifferentClasses() {
		System.out.println("DIFFERENT ENTITIES WITH SAME IDS");
		
        Address address = new Address();
        address.setId(UUID.fromString("622da2a8-85ea-11ea-bc55-0242ac130003"));
        printHash(address);
        
        Order order = new Order();
        order.setId(UUID.fromString("622da2a8-85ea-11ea-bc55-0242ac130003"));
        printHash(order);
        
        assertFalse(Objects.equals((Object) address, (Object) order));
	}
	
	@Test
	void testIdClass() {
		Product product = new Meal();
		Store store = new Store();
		
		Stock stock1 = new Stock();
		stock1.setProduct(product);
		stock1.setStore(store);
		
		Stock stock2 = new Stock();
		stock2.setProduct(product);
		stock2.setStore(store);
		
		assertTrue(stock1.equals(stock2));
	}
	
	private void printHash(Object o) {
		System.out.println("Hash: " + o.hashCode());
	}
	
}
