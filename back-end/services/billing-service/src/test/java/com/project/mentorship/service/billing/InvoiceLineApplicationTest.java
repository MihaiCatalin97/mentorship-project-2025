package com.project.mentorship.service.billing;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

public class InvoiceLineApplicationTest {
	@Test
	void mainMethodRunsWithoutException() {
		assertDoesNotThrow(() -> InvoiceLineServiceApplication.main(new String[]{}));
	}
}
