package com.project.mentorship.service.auth.persistance;

import com.project.mentorship.lib.pattern.BaseRepository;
import com.project.mentorship.service.auth.domain.Role;
import com.project.mentorship.service.auth.domain.User;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class UserRepository implements BaseRepository<User> {
	private final List<User> users = new ArrayList<>();

	{
		User admin = new User();
		admin.setId(UUID.randomUUID());
		admin.setUsername("admin");
		admin.setEmail("admin@gmail.com");
		admin.setPasswordHash("admin");
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
