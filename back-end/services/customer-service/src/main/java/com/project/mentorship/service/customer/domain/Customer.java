package com.project.mentorship.service.customer.domain;

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
public class Customer {
	private UUID id;
	private UUID userId;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private OffsetDateTime createdAt;
	private OffsetDateTime updatedAt;
}
