package com.project.mentorship.service.notification.mapper;

import com.project.mentorship.service.notification.api.dto.NotificationDto;
import com.project.mentorship.service.notification.domain.Notification;
import com.project.mentorship.service.notification.domain.NotificationStatus;
import com.project.mentorship.service.notification.domain.NotificationType;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {
	public Notification map(NotificationDto dto) {
		if (dto == null) {
			return null;
		}

		return Notification.builder().id(dto.id()).reservationId(dto.reservationId()).customerId(dto.customerId())
				.type(dto.type() != null ? NotificationType.valueOf(dto.type()) : null)
				.status(dto.status() != null ? NotificationStatus.valueOf(dto.status()) : null).sentAt(dto.sentAt())
				.createdAt(dto.createdAt()).build();
	}

	public NotificationDto map(Notification notification) {
		if (notification == null) {
			return null;
		}

		return new NotificationDto(notification.getId(), notification.getReservationId(), notification.getCustomerId(),
				notification.getType() != null ? notification.getType().name() : null,
				notification.getStatus() != null ? notification.getStatus().name() : null, notification.getSentAt(),
				notification.getCreatedAt());
	}
}
