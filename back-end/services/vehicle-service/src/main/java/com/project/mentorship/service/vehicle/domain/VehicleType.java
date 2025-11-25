package com.project.mentorship.service.vehicle.domain;

import java.math.BigDecimal;
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
	private String name;
	private Double hourlyRate;
	private Integer capacity;
	private OffsetDateTime createdAt;
	private OffsetDateTime updatedAt;
}
