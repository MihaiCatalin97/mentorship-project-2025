package com.project.mentorship.service.vehicle.api.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record VehicleTypeDto(UUID id, String name, Double hourlyRate, Integer capacity, OffsetDateTime createdAt,
		OffsetDateTime updatedAt) {
}
