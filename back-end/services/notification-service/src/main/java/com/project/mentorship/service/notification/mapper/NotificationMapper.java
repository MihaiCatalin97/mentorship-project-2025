package com.project.mentorship.service.notification.mapper;

import com.project.mentorship.contract.notification.model.NotificationDto;
import com.project.mentorship.service.notification.domain.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {
	public Notification map(NotificationDto dto) {
		if (dto == null) {
			return null;
		}

		return Notification.builder().id(dto.getId()).reservationId(dto.getReservationId())
				.customerId(dto.getCustomerId()).type(dto.getType() != null ? dto.getType() : null)
				.status(dto.getStatus() != null ? dto.getStatus() : null).sentAt(dto.getSentAt())
				.createdAt(dto.getCreatedAt()).build();
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
