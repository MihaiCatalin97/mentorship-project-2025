package com.project.mentorship.service.notification;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

class NotificationServiceApplicationTest {

	@Test
	void contextLoads() {
		assertDoesNotThrow(() -> NotificationServiceApplication.main(new String[]{}));
	}
}
