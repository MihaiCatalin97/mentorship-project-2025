package com.project.mentorship.service.auth.api.dto;

import com.project.mentorship.service.auth.domain.Role;
import java.time.OffsetDateTime;
import java.util.UUID;

public record UserDto(UUID id, String username, String password, String email, Role role, OffsetDateTime createdAt,
		OffsetDateTime updatedAt) {
}
