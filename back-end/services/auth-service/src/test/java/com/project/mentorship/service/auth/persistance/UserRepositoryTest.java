package com.project.mentorship.service.auth.persistance;

import static org.assertj.core.api.Assertions.assertThat;

import com.project.mentorship.service.auth.domain.Role;
import com.project.mentorship.service.auth.domain.User;
import org.junit.jupiter.api.Test;

public class UserRepositoryTest {

	@Test
	void save_sets_id_role_and_timestamps_and_returns_user() {
		UserRepository userRepository = new UserRepository();
		User user = new User();
		user.setUsername("alex");
		user.setEmail("alex@gmail.com");
		user.setPasswordHash("1234");

		User savedUser = userRepository.save(user);

		assertThat(savedUser.getId()).isNotNull();
		assertThat(savedUser.getRole()).isEqualTo(Role.USER);
		assertThat(savedUser.getCreatedAt()).isNotNull();
		assertThat(savedUser.getUpdatedAt()).isNotNull();
		assertThat(savedUser).isSameAs(user);

	}
}
