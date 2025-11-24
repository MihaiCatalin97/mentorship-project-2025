package com.project.mentorship.service.auth.mapper;

import com.project.mentorship.service.auth.api.dto.UserDto;
import com.project.mentorship.service.auth.domain.User;
import java.util.Objects;

public class UserMapper {

	public static User toDomain(UserDto userDto) {
		Objects.requireNonNull(userDto, "userDto must not be null");
		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		user.setPasswordHash(userDto.getPassword());
		return user;
	}

	public static UserDto toDto(User user) {
		Objects.requireNonNull(user, "user must not be null");
		UserDto userDto = new UserDto();
		userDto.setUsername(user.getUsername());
		userDto.setEmail(user.getEmail());
		return userDto;
	}

}
