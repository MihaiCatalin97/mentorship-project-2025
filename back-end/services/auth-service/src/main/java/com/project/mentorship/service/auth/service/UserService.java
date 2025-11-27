package com.project.mentorship.service.auth.service;

import com.project.mentorship.lib.pattern.BaseRepository;
import com.project.mentorship.lib.pattern.BaseService;
import com.project.mentorship.service.auth.domain.User;
import com.project.mentorship.service.auth.persistance.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements BaseService<User> {
	private final BaseRepository<User> userRepository;
    private final EncryptionService encryptionService;

    @Override
	public User create(User user) {
		user.setPasswordHash(encryptionService.hash(user.getPasswordHash()));
        return userRepository.save(user);
	}
}
