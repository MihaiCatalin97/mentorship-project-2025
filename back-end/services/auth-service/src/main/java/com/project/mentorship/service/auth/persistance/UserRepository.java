package com.project.mentorship.service.auth.persistance;

import com.project.mentorship.lib.pattern.BaseRepository;
import com.project.mentorship.service.auth.domain.Role;
import com.project.mentorship.service.auth.domain.User;
import com.project.mentorship.service.auth.service.EncryptionService;
import jakarta.annotation.PostConstruct;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository implements BaseRepository<User> {
	private final List<User> users = new ArrayList<>();
	private final EncryptionService encryptionService;

	@Value("${service.auth.user.admin.username}")
	private String adminUsername;

	@Value("${service.auth.user.admin.email}")
	private String adminEmail;

	@Value("${service.auth.user.admin.password}")
	private String adminPassword;

	@PostConstruct
	private void createDefaultAdmin() {
		User admin = new User();
		admin.setId(UUID.randomUUID());
		admin.setUsername(adminUsername);
		admin.setEmail(adminEmail);
		admin.setPasswordHash(encryptionService.hash(adminPassword));
		admin.setRole(Role.ADMIN);
		admin.setCreatedAt(OffsetDateTime.now());
		admin.setUpdatedAt(OffsetDateTime.now());
		users.add(admin);
	}
	@Override
	public User save(User user) {
		if (user.getId() == null) {
			user.setId(UUID.randomUUID());
		}
		if (user.getRole() == null) {
			user.setRole(Role.USER);
		}
		if (user.getCreatedAt() == null) {
			user.setCreatedAt(OffsetDateTime.now());
		}
		user.setUpdatedAt(OffsetDateTime.now());
		users.add(user);
		return user;
	}

}
