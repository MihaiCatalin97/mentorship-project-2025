package com.project.mentorship.service.notification.actuator;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.junit.jupiter.api.Test;

class HealthCheckEndpointTest {

	@Test
	void health_shouldReturnStatusUp() {
		HealthCheckEndpoint endpoint = new HealthCheckEndpoint();
		Map<String, String> result = endpoint.health();
		assertThat(result).containsEntry("status", "UP");
	}
}