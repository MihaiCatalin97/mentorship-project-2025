package com.project.mentorship.service.customer;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class CustomerServiceApplicationTest {

	@Test
	void main_shouldRunWithoutExceptions_whenApplicationStarts() {
		assertDoesNotThrow(() -> CustomerServiceApplication.main(new String[]{}));
	}
}
