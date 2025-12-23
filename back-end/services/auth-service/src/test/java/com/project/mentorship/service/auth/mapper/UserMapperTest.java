package com.project.mentorship.service.auth.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.project.mentorship.contract.auth.model.UserDto;
import com.project.mentorship.service.auth.domain.Role;
import com.project.mentorship.service.auth.domain.User;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class UserMapperTest {

	@Test
	void toDomain_shouldMapInputFieldsAndLeaveServerFieldsNull_whenDtoIsValid() {
		// Given
		UserDto userDto = new UserDto("alex", "1234", "alex@gmail.com");

		UserMapper userMapper = new UserMapper();

		// When
		User user = userMapper.toDomain(userDto);

		// Then
		assertThat(user.getUsername()).isEqualTo("alex");
		assertThat(user.getEmail()).isEqualTo("alex@gmail.com");
		assertThat(user.getPasswordHash()).isEqualTo("1234");
	}

	@Test
	void toDto_shouldExposeOnlySafeFields_whenMappingFromDomain() {
		// Given
		UUID id = UUID.randomUUID();
		OffsetDateTime createdAt = OffsetDateTime.now().minusSeconds(60);
		OffsetDateTime updatedAt = OffsetDateTime.now();

		UserMapper userMapper = new UserMapper();
		User user = new User();
		user.setId(id);
		user.setUsername("alex");
		user.setEmail("alex@gmail.com");
		user.setPasswordHash("secret");
		user.setRole(Role.USER);
		user.setCreatedAt(createdAt);
		user.setUpdatedAt(updatedAt);

		// When
		UserDto dto = userMapper.toDto(user);

		// Then
		assertThat(dto.getId()).isEqualTo(id);
		assertThat(dto.getUsername()).isEqualTo("alex");
		assertThat(dto.getEmail()).isEqualTo("alex@gmail.com");
		assertThat(dto.getRole()).isEqualTo(UserDto.RoleEnum.USER);
		assertThat(dto.getCreatedAt()).isEqualTo(createdAt);
		assertThat(dto.getUpdatedAt()).isEqualTo(updatedAt);
		assertThat(dto.getPassword()).isNull();
	}
}
