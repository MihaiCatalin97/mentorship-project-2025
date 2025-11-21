package com.project.mentorship.service.reservation.domain;

import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Accessors(chain = true)
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