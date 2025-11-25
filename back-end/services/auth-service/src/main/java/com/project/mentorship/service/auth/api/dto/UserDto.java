package com.project.mentorship.service.auth.api.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {
    private UUID id;
	private String username;
	private String email;
	private String password;
}
