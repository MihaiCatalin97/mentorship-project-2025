package com.project.mentorship.service.auth.persistance;

import com.project.mentorship.lib.pattern.BaseRepository;
import com.project.mentorship.service.auth.domain.Role;
import com.project.mentorship.service.auth.domain.User;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.project.mentorship.service.auth.service.EncryptionService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Component
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
    private void createDefaultAdmin()
	{
		User admin = new User();
		admin.setId(UUID.randomUUID());
		admin.setUsername(adminUsername);
		admin.setEmail(adminEmail);
		admin.setPasswordHash(encryptionService.hash(adminPassword));
		admin.setRole(Role.ADMIN);
		admin.setCreatedAt(Timestamp.from(Instant.now()));
		admin.setUpdatedAt(Timestamp.from(Instant.now()));
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
			user.setCreatedAt(Timestamp.from(Instant.now()));
		}
		user.setUpdatedAt(Timestamp.from(Instant.now()));
		users.add(user);
		return user;
	}

}
