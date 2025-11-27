package com.project.mentorship.service.auth.mapper;

import com.project.mentorship.service.auth.api.dto.UserDto;
import com.project.mentorship.service.auth.domain.User;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserMapper {

	public User toDomain(UserDto userDto) {
		Objects.requireNonNull(userDto, "userDto must not be null");

		User user = new User();
		user.setUsername(userDto.username());
		user.setEmail(userDto.email());
		user.setPasswordHash(userDto.password());

		return user;
	}

	public UserDto toDto(User user) {
		Objects.requireNonNull(user, "user must not be null");
        return new UserDto(
                user.getId(),
                user.getUsername(),
                null,
                user.getEmail(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
	}

}
