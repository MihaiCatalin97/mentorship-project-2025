package com.project.mentorship.service.reservation;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class ReservationServiceApplicationTest {

	@Test
	void mainMethodRunsWithoutException() {
		assertDoesNotThrow(() -> ReservationServiceApplication.main(new String[]{}));
	}
}
