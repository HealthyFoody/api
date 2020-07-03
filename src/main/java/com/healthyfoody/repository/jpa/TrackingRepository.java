package com.healthyfoody.repository.jpa;

import com.healthyfoody.entity.Tracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TrackingRepository extends JpaRepository<Tracking, UUID> {

    List<Tracking> findAllByOrderId(UUID uuid);
}
