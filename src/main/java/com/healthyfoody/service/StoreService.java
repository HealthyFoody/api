package com.healthyfoody.service;

import java.util.List;
import java.util.UUID;

import com.healthyfoody.dto.response.StoreResponse;
import com.healthyfoody.entity.Store;

public interface StoreService extends ResourceService<StoreResponse, Store, UUID> {

    List<StoreResponse> findNearbyStores(double latitude, double longitude);

    Boolean inRangeOfStore(UUID storeId, double latitude, double longitude);
}
