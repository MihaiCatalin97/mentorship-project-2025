package com.project.mentorship.service.notification.api;

import com.project.mentorship.contract.notification.api.NotificationsApiDelegate;
import com.project.mentorship.contract.notification.model.NotificationDto;
import com.project.mentorship.lib.pattern.BaseService;
import com.project.mentorship.service.notification.domain.Notification;
import com.project.mentorship.service.notification.mapper.NotificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationApiDelegateImpl implements NotificationsApiDelegate {

	private final BaseService<Notification> notificationService;
	private final NotificationMapper notificationMapper;

	@Override
	public ResponseEntity<NotificationDto> createNotification(NotificationDto request) {
		Notification notification = notificationMapper.map(request);
		Notification createdNotification = notificationService.create(notification);
		NotificationDto response = notificationMapper.map(createdNotification);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
