package com.healthyfoody.api.location;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService {

	@Autowired
	StoreRepository storeRepository;

	public Optional<Store> findById(UUID id) {
		return storeRepository.findById(id);
	}
	
	public List<Store> findNearbyStores(Address address) {
		
		List<Store> stores = storeRepository.findAll()
				.stream().filter(x -> 
					distance(x.getLatitude(), address.getLatitude(), 
							x.getLongitude(), address.getLongitude()) <= 5000
				).collect(Collectors.toList());
		
		return stores;
	}
	
	private Double distance(double lat1, double lat2, double lon1, double lon2) {

	    final int R = 6371; // Radius of the earth

	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c * 1000; // convert to meters

	    return Math.sqrt(distance);
	}
}
