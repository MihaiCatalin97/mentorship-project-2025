package com.project.mentorship.service.auth.service;

import com.project.mentorship.lib.pattern.BaseRepository;
import com.project.mentorship.lib.pattern.BaseService;
import com.project.mentorship.service.auth.domain.Role;
import com.project.mentorship.service.auth.domain.User;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements BaseService<User> {
	private final BaseRepository<User> userRepository;
	private final EncryptionService encryptionService;

	@Override
	public User create(User user) {
		user.setId(UUID.randomUUID());
		user.setRole(Role.USER);
		user.setCreatedAt(OffsetDateTime.now(ZoneOffset.UTC));
		user.setUpdatedAt(null);
		user.setPasswordHash(encryptionService.hash(user.getPasswordHash()));
		return userRepository.save(user);
	}

    @Override
    public java.util.Optional<User> findById(String id) {
        return userRepository.findById(id);
    }
}
