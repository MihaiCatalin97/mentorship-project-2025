package com.project.mentorship.service.reservation.api.dto;

import com.project.mentorship.service.reservation.domain.ReservationStatus;
import java.time.OffsetDateTime;
import java.util.UUID;

public record ReservationDto(UUID id, UUID customerId, UUID vehicleId, OffsetDateTime startTime, OffsetDateTime endTime,
		ReservationStatus status, OffsetDateTime createdAt, OffsetDateTime updatedAt) {
}
