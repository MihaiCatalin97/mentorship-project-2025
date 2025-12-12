package com.project.mentorship.service.gateway;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
class GatewayApplicationTest {

	@Test
	void main_shouldRunWithoutExceptions() {
		assertDoesNotThrow(() -> GatewayApplication.main(new String[]{}));
	}
}
