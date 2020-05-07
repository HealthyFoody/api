package com.healthyfoody.repository.jpa;

import com.healthyfoody.entity.Tracking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TrackingRepository extends JpaRepository<Tracking, UUID> {

    List<Tracking> findAllByOrderId(UUID uuid);
}
