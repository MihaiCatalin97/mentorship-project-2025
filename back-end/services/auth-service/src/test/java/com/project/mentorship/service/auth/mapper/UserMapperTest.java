package com.project.mentorship.service.auth.mapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.project.mentorship.service.auth.api.dto.UserDto;
import com.project.mentorship.service.auth.domain.User;
import org.junit.jupiter.api.Test;

public class UserMapperTest {

	@Test
	void toDomain_maps_input_fields_and_leaves_server_fields_null() {
		UserDto userDto = new UserDto();
		userDto.setUsername("alex");
		userDto.setEmail("alex@gmail.com");
		userDto.setPassword("1234");

		User user = UserMapper.toDomain(userDto);

		assertThat(user.getUsername()).isEqualTo("alex");
		assertThat(user.getEmail()).isEqualTo("alex@gmail.com");
		assertThat(user.getPasswordHash()).isEqualTo("1234");

		assertThat(user.getId()).isNull();
		assertThat(user.getRole()).isNull();
		assertThat(user.getCreatedAt()).isNull();
		assertThat(user.getUpdatedAt()).isNull();
	}
	@Test
	void toDto_exposes_only_safe_fields() {
		User user = new User();
		user.setUsername("alex");
		user.setEmail("alex@gmail.com");
		user.setPasswordHash("secret");

		var dto = UserMapper.toDto(user);

		assertThat(dto.getUsername()).isEqualTo("alex");
		assertThat(dto.getEmail()).isEqualTo("alex@gmail.com");
	}
}
