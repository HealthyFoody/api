package com.healthyfoody.service;

import com.healthyfoody.entity.Task.DeliveryTask;
import com.healthyfoody.entity.Tracking;

public interface TrackingService {
    Tracking createTracking(DeliveryTask task, int stepNumber);
}
