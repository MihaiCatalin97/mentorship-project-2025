package com.project.mentorship.service.analytics.domain;

import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statistics {

	private UUID id;
	private OffsetDateTime date;
	private Integer totalReservations;
	private Double totalRevenue;
	private OffsetDateTime createdAt;
}
