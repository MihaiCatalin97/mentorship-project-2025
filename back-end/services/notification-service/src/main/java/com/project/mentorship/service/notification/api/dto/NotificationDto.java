package com.project.mentorship.service.notification.api.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record NotificationDto(UUID id, UUID reservationId, UUID customerId, String type, String status,
		OffsetDateTime sentAt, OffsetDateTime createdAt) {
}
