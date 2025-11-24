package com.project.mentorship.service.auth.service;

import com.project.mentorship.service.auth.domain.User;
import com.project.mentorship.service.auth.persistance.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User create(User user) {
		return userRepository.save(user);
	}
}
