package com.healthyfoody.service.impl;

import com.healthyfoody.entity.Task;
import com.healthyfoody.entity.Tracking;
import com.healthyfoody.repository.jpa.TaskRepository;
import com.healthyfoody.service.DeliveryTask;
import com.healthyfoody.service.TrackingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class TrackingServiceImpl implements TrackingService {

    private final static LocalTime DELIVERY_TIME = LocalTime.of(0,30);
    private final static LocalTime PREPARING_TIME = LocalTime.of(0,15);
    private final static LocalTime PAYMENT_WINDOW = LocalTime.of(0,5);

    @Autowired
    TaskRepository taskRepository;

    @Override
    public Tracking createTracking(DeliveryTask task, int stepNumber) {

        Task aux = taskRepository.findByCode(task.code).orElse(null);
        Tracking tracking = new Tracking();
        tracking.setStepNumber(stepNumber);
        tracking.setTask(aux);

        switch (task) {
            case PREPARING:
                tracking.setEstimatedTime(PREPARING_TIME);
            case SENDING:
                tracking.setEstimatedTime(DELIVERY_TIME);
            case AWAITING_PAYMENT:
                tracking.setEstimatedTime(PAYMENT_WINDOW);
        }

        return tracking;
    }
}
