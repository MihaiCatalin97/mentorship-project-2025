package com.project.mentorship.service.vehicle;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class VehicleServiceApplicationTest {

	@Test
	void main_shouldRunWithoutException_whenInvoked() {
		assertDoesNotThrow(() -> VehicleServiceApplication.main(new String[]{}));
	}
}
