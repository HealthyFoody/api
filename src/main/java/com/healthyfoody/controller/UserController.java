package com.healthyfoody.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/get")
    public ResponseEntity<?> getByEmail(@RequestParam String email) {
        //TODO: implement controller method
        return null;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        //TODO: implement controller method
        return null;
    }
}
