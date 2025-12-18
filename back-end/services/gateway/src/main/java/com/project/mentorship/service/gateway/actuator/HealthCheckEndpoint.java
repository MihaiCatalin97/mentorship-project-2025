package com.project.mentorship.service.gateway.actuator;

import java.util.Map;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "healthcheckendpoint")
public class HealthCheckEndpoint {

	@ReadOperation
	public Map<String, String> health() {
		return Map.of("status", "UP");
	}
}
