package com.project.mentorship.service.auth.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.project.mentorship.service.auth.domain.User;
import com.project.mentorship.service.auth.persistance.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private EncryptionService encryptionService;

	@InjectMocks
	private UserService userService;

	@Test
	void create_shouldHashPasswordAndSaveUser_whenUserIsValid() {
		// Given
		User input = new User();
		input.setPasswordHash("plain");
		User savedUser = new User();

		when(encryptionService.hash("plain")).thenReturn("hashed");
		when(userRepository.save(input)).thenReturn(savedUser);

		// When
		User result = userService.create(input);

		// Then
		verify(encryptionService, times(1)).hash("plain");
		verify(userRepository, times(1)).save(input);
		assertThat(input.getPasswordHash()).isEqualTo("hashed");
		assertThat(result).isSameAs(savedUser);
	}
}
