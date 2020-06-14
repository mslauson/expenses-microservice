/*
 * Copyright (c) 2020. Blossom Budgeting LLC
 * All Rights Reserved
 */

package io.blossom.microservices.expenses.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @GetMapping("/")
    public ResponseEntity<String> getHealthStatus() {
        return ResponseEntity.ok("healthy");
    }

    @GetMapping("/expenses")
    public ResponseEntity<String> getHealthStatus2() {
        return ResponseEntity.ok("healthy");
    }
}
