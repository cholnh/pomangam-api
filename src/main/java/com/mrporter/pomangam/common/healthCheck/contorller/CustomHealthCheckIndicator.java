package com.mrporter.pomangam.common.healthCheck.contorller;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicReference;

@Deprecated
@RestController
@RequestMapping("${management.endpoints.web.base-path:/application}")
public class CustomHealthCheckIndicator implements HealthIndicator{
    private final AtomicReference<Health> health = new AtomicReference<>(Health.up().build());

    @Override
    public Health health() {
        return health.get();
    }

    @PreAuthorize("isAuthenticated() and !hasRole('ROLE_ADMIN')")
    @PutMapping("${management.endpoints.web.path-mapping.healthCheck}/up")
    public Health up() {
        System.out.println("up");
        Health up = Health.up().build();
        this.health.set(up);
        return up;
    }

    @PreAuthorize("isAuthenticated() and !hasRole('ROLE_ADMIN')")
    @PutMapping("${management.endpoints.web.path-mapping.healthCheck}/down")
    public Health down() {
        System.out.println("down");
        Health down = Health.down().build();
        this.health.set(down);
        return down;
    }

    @PreAuthorize("isAuthenticated() and !hasRole('ROLE_ADMIN')")
    @PutMapping("${management.endpoints.web.path-mapping.healthCheck}/maintenance")
    public Health maintenance() {
        Health maintenance = Health.status(new Status("MAINTENANCE", "점검중")).build();
        this.health.set(maintenance);
        return maintenance;
    }
}
