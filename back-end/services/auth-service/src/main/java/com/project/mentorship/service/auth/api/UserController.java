package com.project.mentorship.service.auth.api;

import com.project.mentorship.service.auth.api.dto.UserDto;
import com.project.mentorship.service.auth.domain.User;
import com.project.mentorship.service.auth.mapper.UserMapper;
import com.project.mentorship.service.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@PostMapping
	public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
		User user = UserMapper.toDomain(userDto);
		User created = userService.create(user);
        UserDto createdDto = UserMapper.toDto(created);
		return ResponseEntity.status(201).body(createdDto);
	}
}
