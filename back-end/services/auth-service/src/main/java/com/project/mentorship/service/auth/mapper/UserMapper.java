package com.project.mentorship.service.auth.mapper;

import com.project.mentorship.contract.auth.model.UserDto;
import com.project.mentorship.service.auth.domain.User;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

	public User toDomain(UserDto userDto) {
		Objects.requireNonNull(userDto, "userDto must not be null");

		User user = new User();
		user.setUsername(userDto.getUsername());
		user.setEmail(userDto.getEmail());
		user.setPasswordHash(userDto.getPassword());

		return user;
	}

	public UserDto toDto(User user) {
		Objects.requireNonNull(user, "user must not be null");

		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setUsername(user.getUsername());
		userDto.setRole(UserDto.RoleEnum.fromValue(user.getRole().name()));
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPasswordHash());
		return userDto;
	}

}
