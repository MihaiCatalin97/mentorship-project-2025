package com.project.mentorship.service.notification.persistance;

import com.project.mentorship.service.notification.domain.Notification;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

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
