package com.project.mentorship.lib.pattern;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.project.mentorship.lib.exception.NotImplementedException;
import org.junit.jupiter.api.Test;

class BaseServiceTest {

	private final BaseService<Object> service = new BaseService<>() {
	};

	@Test
	void create_ShouldThrowNotImplementedException() {
		assertThrows(NotImplementedException.class, () -> service.create(new Object()));
	}

	@Test
	void update_ShouldThrowNotImplementedException() {
		assertThrows(NotImplementedException.class, () -> service.update(new Object()));
	}

	@Test
	void delete_ShouldThrowNotImplementedException() {
		assertThrows(NotImplementedException.class, () -> service.delete("id"));
	}

	@Test
	void findById_ShouldThrowNotImplementedException() {
		assertThrows(NotImplementedException.class, () -> service.findById("id"));
	}

	@Test
	void findAll_ShouldThrowNotImplementedException() {
		assertThrows(NotImplementedException.class, service::findAll);
	}
}
