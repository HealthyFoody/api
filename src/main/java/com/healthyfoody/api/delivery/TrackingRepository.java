package com.healthyfoody.api.delivery;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

interface TrackingRepository extends JpaRepository<Tracking, UUID> {

    List<Tracking> findAllByOrderId(UUID uuid);
}
