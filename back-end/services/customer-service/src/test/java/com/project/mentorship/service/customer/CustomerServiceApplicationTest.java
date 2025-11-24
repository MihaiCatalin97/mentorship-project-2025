package com.project.mentorship.service.customer;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

public class CustomerServiceApplicationTest {

	@Test
	void mainMethodRunsWithoutException() {
		assertDoesNotThrow(() -> CustomerServiceApplication.main(new String[]{}));
	}
}
