package com.project.mentorship.service.vehicle;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class VehicleTypeServiceApplicationTest {

	@Test
	void mainMethodRunsWithoutException() {
		assertDoesNotThrow(() -> VehicleTypeServiceApplication.main(new String[]{}));
	}
}
