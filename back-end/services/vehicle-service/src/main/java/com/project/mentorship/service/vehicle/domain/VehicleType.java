package com.project.mentorship.service.vehicle.domain;

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
public class VehicleType {
	private UUID id;
	private String licensePlate;
	private String brand;
	private String model;
	private Integer year;
	private VehicleStatus status;
	private String location;
	private OffsetDateTime createdAt;
	private OffsetDateTime updatedAt;
}
