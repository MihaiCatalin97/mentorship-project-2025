package com.project.mentorship.service.auth.api.dto;

import lombok.Data;

@Data
public class UserDto {
	private String username;
	private String email;
	private String password;
}
