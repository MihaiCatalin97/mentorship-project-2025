package com.project.mentorship.service.notification.mapper;

import com.project.mentorship.contract.notification.model.NotificationDto;
import com.project.mentorship.service.notification.domain.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {
	public Notification map(NotificationDto notificationDto) {
		if (notificationDto == null) {
			return null;
		}

		return Notification.builder().id(notificationDto.getId()).reservationId(notificationDto.getReservationId())
				.customerId(notificationDto.getCustomerId())
				.type(notificationDto.getType() != null ? notificationDto.getType() : null)
				.status(notificationDto.getStatus() != null ? notificationDto.getStatus() : null)
				.sentAt(notificationDto.getSentAt()).createdAt(notificationDto.getCreatedAt()).build();
	}

	public NotificationDto map(Notification notification) {
		if (notification == null) {
			return null;
		}

		NotificationDto notificationDto = new NotificationDto();

		notificationDto.setId(notification.getId());
		notificationDto.setReservationId(notification.getReservationId());
		notificationDto.setCustomerId(notification.getCustomerId());
		notificationDto.setType(notification.getType());
		notificationDto.setStatus(notification.getStatus());
		notificationDto.setSentAt(notification.getSentAt());
		notificationDto.setCreatedAt(notification.getCreatedAt());

		return notificationDto;
	}
}
