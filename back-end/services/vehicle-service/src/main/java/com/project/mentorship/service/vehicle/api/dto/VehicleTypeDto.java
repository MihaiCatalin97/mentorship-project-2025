package com.project.mentorship.service.vehicle.api.dto;

import com.project.mentorship.service.vehicle.domain.VehicleStatus;
import java.time.OffsetDateTime;
import java.util.UUID;

public record VehicleTypeDto(
        UUID id,
        String licensePlate,
        String brand,
        String model,
        Integer year,
		VehicleStatus status,
        String location,
        OffsetDateTime createdAt,
        OffsetDateTime updatedAt) {
}
