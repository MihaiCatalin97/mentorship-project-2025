package com.project.mentorship.service.analytics.api.dto;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

public record StatisticsDto(UUID id, OffsetDateTime date, Integer totalReservations, BigDecimal totalRevenue,
		OffsetDateTime createdAt) {
}
