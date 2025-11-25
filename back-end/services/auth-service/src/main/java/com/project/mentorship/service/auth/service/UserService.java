package com.project.mentorship.service.auth.service;

import com.project.mentorship.service.auth.domain.User;
import com.project.mentorship.service.auth.persistance.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
    private final EncryptionService encryptionService;

	public User create(User user) {
		user.setPasswordHash(encryptionService.hash(user.getPasswordHash()));
        return userRepository.save(user);
	}
}
