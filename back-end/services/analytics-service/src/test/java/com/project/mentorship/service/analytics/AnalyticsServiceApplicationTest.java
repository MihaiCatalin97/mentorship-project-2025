package com.project.mentorship.service.analytics;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AnalyticsServiceApplicationTest {

	@Test
	void mainMethodRuns() {
		assertDoesNotThrow(() -> AnalyticsServiceApplication.main(new String[]{}));
	}
}
