package com.project.mentorship.service.auth.domain;

import java.sql.Timestamp;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class User {
	private UUID id;
	private String username;
	private String email;
	private Role role;
	private String passwordHash;
	private Timestamp createdAt;
	private Timestamp updatedAt;
}
