package com.project.mentorship.service.auth.persistance;

import static org.assertj.core.api.Assertions.assertThat;

import com.project.mentorship.service.auth.domain.Role;
import com.project.mentorship.service.auth.domain.User;
import com.project.mentorship.service.auth.service.EncryptionService;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserRepositoryTest {

    @Test
    void save_shouldSetDefaultFields_whenUserHasNullValues() {
        // Given
        UserRepository userRepository = new UserRepository(new EncryptionService());
        User user = new User();
        user.setUsername("alex");
        user.setEmail("alex@gmail.com");
        user.setPasswordHash("1234");

        // When
        User savedUser = userRepository.save(user);

        // Then
        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getRole()).isEqualTo(Role.USER);
        assertThat(savedUser.getCreatedAt()).isNotNull();
        assertThat(savedUser.getUpdatedAt()).isNotNull();
    }

    @Test
    void save_shouldPreserveExistingValues_whenFieldsAreAlreadySet() {
        // Given
        UserRepository userRepository = new UserRepository(new EncryptionService());
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setRole(Role.ADMIN);
        user.setCreatedAt(Timestamp.from(Instant.now().minusSeconds(3600)));

        // When
        User savedUser = userRepository.save(user);

        // Then
        assertThat(savedUser.getId()).isNotNull();
        assertThat(savedUser.getRole()).isEqualTo(Role.ADMIN);
        assertThat(savedUser.getCreatedAt()).isNotNull();
        assertThat(savedUser.getUpdatedAt()).isNotNull();
    }

}
