package com.project.mentorship.service.auth.api;

import com.project.mentorship.lib.pattern.BaseService;
import com.project.mentorship.service.auth.api.dto.UserDto;
import com.project.mentorship.service.auth.domain.User;
import com.project.mentorship.service.auth.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

	private final BaseService<User> userService;
	private final UserMapper userMapper;

	@PostMapping
	public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
		User user = userMapper.toDomain(userDto);
		User created = userService.create(user);
		UserDto createdDto = userMapper.toDto(created);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
	}
}
