package com.project.mentorship.service.notification.service;

import com.project.mentorship.service.notification.domain.Notification;
import com.project.mentorship.service.notification.persistance.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    private NotificationRepository repository;

    @InjectMocks
    private NotificationService service;

    @Test
    void create_ShouldCallRepositoryAndSetId() {
        // given
        Notification notification = new Notification();
        when(repository.save(any(Notification.class))).thenAnswer(inv -> inv.getArgument(0));

        // when
        Notification result = service.create(notification);

        // then
        assertNotNull(result.getId());
        verify(repository).save(result);
    }
}
