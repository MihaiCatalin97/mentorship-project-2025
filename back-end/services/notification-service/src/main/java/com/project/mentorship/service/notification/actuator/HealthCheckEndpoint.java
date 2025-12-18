package com.project.mentorship.service.notification.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Endpoint(id = "healthcheckendpoint")
public class HealthCheckEndpoint {

    @ReadOperation
    public Map<String, String> health() {
        return Map.of("status", "UP");
    }
}