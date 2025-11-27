package com.project.mentorship.service.auth.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.project.mentorship.service.auth.api.dto.UserDto;
import com.project.mentorship.service.auth.domain.Role;
import com.project.mentorship.service.auth.domain.User;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class UserMapperTest {

    @Test
    void toDomain_shouldMapInputFieldsAndLeaveServerFieldsNull_whenDtoIsValid() {
        // Given
        UserDto userDto = new UserDto(
                null,
                "alex",
                "1234",
                "alex@gmail.com",
                null,
                null,
                null
        );

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
        UUID id = UUID.randomUUID();
        Timestamp createdAt = Timestamp.from(Instant.now().minusSeconds(60));
        Timestamp updatedAt = Timestamp.from(Instant.now());

        User user = new User();
        user.setId(id);
        user.setUsername("alex");
        user.setEmail("alex@gmail.com");
        user.setPasswordHash("secret");
        user.setRole(Role.USER);
        user.setCreatedAt(createdAt);
        user.setUpdatedAt(updatedAt);

        // When
        UserDto dto = UserMapper.toDto(user);

        // Then
        assertThat(dto.id()).isEqualTo(id);
        assertThat(dto.username()).isEqualTo("alex");
        assertThat(dto.email()).isEqualTo("alex@gmail.com");
        assertThat(dto.role()).isEqualTo(Role.USER);
        assertThat(dto.createdAt()).isEqualTo(createdAt);
        assertThat(dto.updatedAt()).isEqualTo(updatedAt);

        // password is input-only, should not be exposed
        assertThat(dto.password()).isNull();
    }
}
