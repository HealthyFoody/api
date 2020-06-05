package com.healthyfoody.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.healthyfoody.dto.response.StoreResponse;
import com.healthyfoody.entity.Store;
import com.healthyfoody.exception.ResourceNotFoundException;
import com.healthyfoody.mapper.StoreMapper;
import com.healthyfoody.repository.jpa.StoreRepository;
import com.healthyfoody.service.StoreService;

@Service
public class StoreServiceImpl implements StoreService {

	private static final double MAX_DISTANCE = 5000;
	
	@Autowired
	StoreRepository storeRepository;
	
	@Autowired
	StoreMapper storeMapper;

	@Override
	public List<StoreResponse> findNearbyStores(double latitude, double longitude) {
		List<Store> stores = storeRepository.findAll();

		List<Store> result = stores.stream()
				.filter(x -> distance(x.getLatitude(), latitude, x.getLongitude(), longitude) <= MAX_DISTANCE)
				.collect(Collectors.toList());
		
		return storeMapper.mapResponseList(result);
	}

	@Override
	public Boolean inRangeOfStore(UUID storeId, double latitude, double longitude) {
		return storeRepository.findById(storeId)
				.map(x -> distance(x.getLatitude(), latitude, x.getLongitude(), longitude) <= MAX_DISTANCE)
				.orElse(false);
	}

	@Override
	public List<StoreResponse> findAll() {
		List<Store> result = storeRepository.findAll();
		return storeMapper.mapResponseList(result);
	}

	@Override
	public StoreResponse findById(UUID id) throws ResourceNotFoundException {
		Store result = findEntityById(id);
		return storeMapper.toResponse(result);
	}

	public double distance(double lat1, double lat2, double lon1, double lon2) {

		final int R = 6371; // Radius of the earth

		double latDistance = Math.toRadians(lat2 - lat1);
		double lonDistance = Math.toRadians(lon2 - lon1);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c * 1000; // convert to meters

		return distance;
	}

	@Override
	public Store findEntityById(UUID id) throws ResourceNotFoundException {
		return storeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id, Store.class));
	}

}
