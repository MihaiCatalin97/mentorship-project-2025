package com.project.mentorship.service.reservation.domain;

import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {
	private UUID id;
	private UUID customerId;
	private UUID vehicleId;
	private OffsetDateTime startTime;
	private OffsetDateTime endTime;
	private ReservationStatus status;
	private OffsetDateTime createdAt;
	private OffsetDateTime updatedAt;
}
