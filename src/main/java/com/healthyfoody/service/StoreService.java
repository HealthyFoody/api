package com.healthyfoody.service;

import com.healthyfoody.entity.Store;

import java.util.List;
import java.util.UUID;

public interface StoreService extends CrudService<Store, UUID> {

    List<Store> findNearbyStores(double latitude, double longitude);

    Boolean inRangeOfStore(UUID storeId, double latitude, double longitude);
}
