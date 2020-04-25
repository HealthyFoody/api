package com.healthyfoody.api.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TrackingService {
    @Autowired
    private TrackingRepository trackingRepository;

    public Optional<Tracking> findById(UUID id) {

        return trackingRepository.findById(id);
    }

    public List<Tracking> findAllByOrderId(UUID orderId) {

        return trackingRepository.findAllByOrderId(orderId);
    }
}
