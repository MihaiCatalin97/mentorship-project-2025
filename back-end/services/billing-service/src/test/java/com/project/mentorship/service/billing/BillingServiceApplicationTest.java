package com.project.mentorship.service.billing;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class BillingServiceApplicationTest {
	@Test
	void mainMethodRunsWithoutException() {
		assertDoesNotThrow(() -> BillingServiceApplication.main(new String[]{}));
	}
}
