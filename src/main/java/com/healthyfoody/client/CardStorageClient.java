package com.healthyfoody.client;

import java.util.Map;

public interface CardStorageClient {

    Map<String, Object> storeCard();

    Map<String, Object> getCards();
}
