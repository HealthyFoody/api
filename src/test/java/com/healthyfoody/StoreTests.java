package com.healthyfoody;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.healthyfoody.entity.Address;
import com.healthyfoody.entity.Store;
import com.healthyfoody.repository.jpa.StoreRepository;
import com.healthyfoody.service.StoreService;
import com.healthyfoody.service.impl.StoreServiceImpl;
@RunWith(MockitoJUnitRunner.class)
public class StoreTests {

	@Autowired
	@InjectMocks
	private StoreServiceImpl storeService;
	
	@Mock
	StoreRepository storeRepository;
	
	@Before
	public void beforeTests() {
		storeService = new StoreServiceImpl();
		System.out.println("llegue al before");
		MockitoAnnotations.initMocks(this);
	}
	@Test
	public void testDistanceInRange() {
		// -12.048264 -77.097946 - -12.054205 - -77.101666
		double distance = storeService.distance(-12.048264,-12.054205,-77.097946,-77.101666);
		System.out.println("Test distance in range " + distance);
		
		boolean result = ( distance <= 5000);
		boolean expected = true;
		assertEquals(expected, result);
	}
	@Test
	public void testDistanceNotInRange() {
		// -12.048264 -77.097946 - -12.086822 - -77.101666
		double distance = storeService.distance(-12.048264,-12.086822,-77.097946,-77.049873);
		System.out.println("Test distance in range " + distance);
		boolean result = ( distance <= 5000);
		boolean expected = false;
		assertEquals(expected, result);
	}
	
	@Test
	public void testNearbyStores() {
		Store store1 = new Store();
		store1.setDescription("store1-test");
		store1.setLatitude(-12.074904);
		store1.setLongitude(-77.082257);
		Store store2 = new Store();
		store2.setDescription("store2-test");
		store2.setLatitude(-12.006115);
		store2.setLongitude(-77.058123);
		Store store3 = new Store();
		store3.setDescription("store3-test");
		store3.setLatitude(-12.04826);
		store3.setLongitude(-76.993221);
		
		List<Store> stores = new ArrayList<Store>();
		stores.add(store1);
		stores.add(store2);
		stores.add(store3);
		
		
		// Mi dirección 
		// -12.042553
		// -77.060856
		double myLat = -12.042553;
		double myLong = -77.060856;
		
		
		
		when(storeRepository.findAll()).thenReturn(stores);
		
		List<Store> result = storeService.findNearbyStores(myLat, myLong); 
		
		int actual = result.size();
		int expected = 2;
		assertEquals(expected,actual);
	}

}
