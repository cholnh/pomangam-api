package com.mrporter.pomangam.client.controllers._bases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/health")
public class HealthCheckIndicator implements HealthIndicator{

    @Autowired
    private AtomicReference<Health> health; // singleton

    @Override
    @GetMapping
    public Health health() {
        return health.get();
    }

    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    @PutMapping("/up")
    public Health up() {
        Health up = Health.up().build();
        this.health.set(up);
        return up;
    }

   @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    @PutMapping("/down")
    public Health down() {
        Health down = Health.down().build();
        this.health.set(down);
        return down;
    }

    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ADMIN')")
    @PutMapping("/maintenance")
    public Health maintenance() {
        Health maintenance = Health.status(new Status("MAINTENANCE", "점검중")).build();
        this.health.set(maintenance);
        return maintenance;
    }
}
