package com.project.mentorship.service.customer.api.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public record CustomerDto(UUID id, UUID userId, String firstName, String lastName, String email, String phone,
		OffsetDateTime createdAt, OffsetDateTime updatedAt) {
}