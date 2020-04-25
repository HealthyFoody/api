package com.healthyfoody.api.delivery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/delivery")
public class TrackingController {

    @Autowired
    private TrackingService trackingService;

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getTrackingById(@PathVariable UUID id) {
        return trackingService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllTrackingPerOrder(@RequestParam("order") UUID orderId) {
        return ResponseEntity.ok(trackingService.findAllByOrderId(orderId));
    }
}
