package com.project.mentorship.service.auth.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.project.mentorship.service.auth.api.dto.UserDto;
import com.project.mentorship.service.auth.domain.User;
import org.junit.jupiter.api.Test;

class UserMapperTest {

    @Test
    void toDomain_shouldMapInputFieldsAndLeaveServerFieldsNull_whenDtoIsValid() {
        // Given
        UserDto userDto = new UserDto();
        userDto.setUsername("alex");
        userDto.setEmail("alex@gmail.com");
        userDto.setPassword("1234");

        // When
        User user = UserMapper.toDomain(userDto);

        // Then
        assertThat(user.getUsername()).isEqualTo("alex");
        assertThat(user.getEmail()).isEqualTo("alex@gmail.com");
        assertThat(user.getPasswordHash()).isEqualTo("1234");

        assertThat(user.getId()).isNull();
        assertThat(user.getRole()).isNull();
        assertThat(user.getCreatedAt()).isNull();
        assertThat(user.getUpdatedAt()).isNull();
    }

    @Test
    void toDto_shouldExposeOnlySafeFields_whenMappingFromDomain() {
        // Given
        User user = new User();
        user.setUsername("alex");
        user.setEmail("alex@gmail.com");
        user.setPasswordHash("secret");
        // When
        UserDto dto = UserMapper.toDto(user);

        // Then
        assertThat(dto.getUsername()).isEqualTo("alex");
        assertThat(dto.getEmail()).isEqualTo("alex@gmail.com");
    }
}
