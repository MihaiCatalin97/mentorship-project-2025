package com.project.mentorship.service.analytics.api.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record StatisticsDto(UUID id, OffsetDateTime date, Integer totalReservations, Double totalRevenue,
		OffsetDateTime createdAt) {
}
