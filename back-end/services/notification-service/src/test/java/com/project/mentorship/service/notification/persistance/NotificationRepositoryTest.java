package com.project.mentorship.service.notification.persistance;

import static org.junit.jupiter.api.Assertions.assertSame;

import com.project.mentorship.service.notification.domain.Notification;
import org.junit.jupiter.api.Test;

class NotificationRepositoryTest {

	@Test
	void save_ShouldReturnSameInstance() {
		// given
		NotificationRepository repository = new NotificationRepository();
		Notification notification = new Notification();

		// when
		Notification saved = repository.save(notification);

		// then
		assertSame(notification, saved);
	}
}
