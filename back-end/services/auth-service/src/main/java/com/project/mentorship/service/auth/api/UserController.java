package com.project.mentorship.service.auth.api;

import com.project.mentorship.lib.pattern.BaseService;
import com.project.mentorship.service.auth.annotations.EndpointCache;
import com.project.mentorship.service.auth.annotations.LogExecutionTime;
import com.project.mentorship.service.auth.annotations.RequiredRoles;
import com.project.mentorship.service.auth.api.dto.UserDto;
import com.project.mentorship.service.auth.domain.User;
import com.project.mentorship.service.auth.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final BaseService<User> userService;
	private final UserMapper userMapper;

	@PostMapping
	@LogExecutionTime
	public ResponseEntity<UserDto> create(@RequestBody UserDto userDto) {
		User user = userMapper.toDomain(userDto);
		User created = userService.create(user);
		UserDto createdDto = userMapper.toDto(created);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
	}

	@GetMapping("/secure-data")
	@RequiredRoles({"ADMIN", "MANAGER"})
	public ResponseEntity<String> secureEndpoint() {
		return ResponseEntity.ok("Secret!");
	}

	@GetMapping
	@EndpointCache(ttlSeconds = 30)
	public ResponseEntity<String> computeUserData(@RequestParam("userId") String userId) {
		simulateSlowApi();
		return ResponseEntity.ok(String.format("User %s has a lot of data!", userId));
	}

	private void simulateSlowApi() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException ignored) {
		}
	}
}
