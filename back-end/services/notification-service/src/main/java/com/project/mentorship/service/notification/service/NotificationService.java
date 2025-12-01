package com.project.mentorship.service.notification.service;

import com.project.mentorship.lib.pattern.BaseRepository;
import com.project.mentorship.lib.pattern.BaseService;
import com.project.mentorship.service.notification.domain.Notification;
import com.project.mentorship.service.notification.domain.NotificationStatus;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService implements BaseService<Notification> {
	private final BaseRepository<Notification> notificationRepository;

	@Override
	public Notification create(Notification notification) {
		notification.setId(UUID.randomUUID());
		notification.setCreatedAt(OffsetDateTime.now(ZoneOffset.UTC));

		if (notification.getStatus() == null) {
			notification.setStatus(NotificationStatus.PENDING);
		}
		return notificationRepository.save(notification);
	}
}
