package com.healthyfoody.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/delivery")
public class TrackingController {

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getTrackingById(@PathVariable UUID id) {
//        return trackingService.findById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());

        //TODO: implement controller method
        return null;
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAllTrackingPerOrder(@RequestParam("order") UUID orderId) {
//        return ResponseEntity.ok(trackingService.findAllByOrderId(orderId));

        //TODO: implement controller method
        return null;
    }
}
