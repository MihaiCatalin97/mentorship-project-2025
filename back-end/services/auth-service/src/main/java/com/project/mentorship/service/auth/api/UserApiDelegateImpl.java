package com.project.mentorship.service.auth.api;

import com.project.mentorship.contract.auth.api.UsersApiDelegate;
import com.project.mentorship.contract.auth.model.UserDto;
import com.project.mentorship.lib.pattern.BaseService;
import com.project.mentorship.service.auth.domain.User;
import com.project.mentorship.service.auth.mapper.UserMapper;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserApiDelegateImpl implements UsersApiDelegate {

	private final BaseService<User> userService;
	private final UserMapper userMapper;

	@Override
	public ResponseEntity<UserDto> createUser(UserDto userDto) {
		User user = userMapper.toDomain(userDto);
		User created = userService.create(user);
		UserDto createdDto = userMapper.toDto(created);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
	}

	@Override
	public ResponseEntity<UserDto> getUserById(UUID uuid) {
		User user = userService.findById(uuid.toString()).get();
		UserDto userDto = userMapper.toDto(user);
		return ResponseEntity.ok(userDto);
	}

}
