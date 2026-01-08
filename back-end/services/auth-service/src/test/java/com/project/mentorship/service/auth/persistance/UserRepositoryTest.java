package com.project.mentorship.service.auth.persistance;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.project.mentorship.service.auth.domain.Role;
import com.project.mentorship.service.auth.domain.User;
import com.project.mentorship.service.auth.service.EncryptionService;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;

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
		assertThat(savedUser).isSameAs(user);
		assertThat(savedUser.getId()).isNull();
		assertThat(savedUser.getRole()).isNull();
		assertThat(savedUser.getCreatedAt()).isNull();
		assertThat(savedUser.getUpdatedAt()).isNull();
	}

	@Test
	void save_shouldPreserveExistingValues_whenFieldsAreAlreadySet() {
		// Given
		UserRepository userRepository = new UserRepository(new EncryptionService());
		User user = new User();
		user.setId(UUID.randomUUID());
		user.setRole(Role.ADMIN);
		user.setCreatedAt(OffsetDateTime.now().minusSeconds(3600));

		// When
		User savedUser = userRepository.save(user);

		// Then
		assertThat(savedUser).isSameAs(user);
		assertThat(savedUser.getId()).isNotNull();
		assertThat(savedUser.getRole()).isEqualTo(Role.ADMIN);
		assertThat(savedUser.getCreatedAt()).isNotNull();
		assertThat(savedUser.getUpdatedAt()).isNull();
	}

	@Test
	void findById_shouldReturnUser_whenUserExists() {
		// Given
		UserRepository userRepository = new UserRepository(new EncryptionService());
		User user = new User();
		user.setId(UUID.randomUUID());
		user.setUsername("alex");
		user.setEmail("alex@email.com");
		userRepository.save(user);

		// When
		User foundUser = userRepository.findById(user.getId().toString()).orElse(null);

		// Then
		assertThat(foundUser).isNotNull();
		assertThat(foundUser.getId()).isEqualTo(user.getId());
	}

	@Test
	void findById_shouldThrowException_whenUserDoesNotExist() {
		// Given
		UserRepository userRepository = new UserRepository(new EncryptionService());

		// When / Then
		assertThatThrownBy(() -> userRepository.findById("missing-id"))
				.isInstanceOf(com.project.mentorship.service.auth.exception.UserNotFoundException.class);
	}
}
