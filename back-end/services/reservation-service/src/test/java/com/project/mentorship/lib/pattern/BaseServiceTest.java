package com.project.mentorship.lib.pattern;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.project.mentorship.lib.exception.NotImplementedException;
import org.junit.jupiter.api.Test;

class BaseServiceTest {

	private final BaseService<Object> service = new BaseService<>() {
	};

	@Test
	void create_ShouldThrowNotImplementedException() {
		Object entity = new Object();
		assertThrows(NotImplementedException.class, () -> service.create(entity));
	}

	@Test
	void update_ShouldThrowNotImplementedException() {
		Object entity = new Object();
		assertThrows(NotImplementedException.class, () -> service.update(entity));
	}

	@Test
	void delete_ShouldThrowNotImplementedException() {
		String id = "id";
		assertThrows(NotImplementedException.class, () -> service.delete(id));
	}

	@Test
	void findById_ShouldThrowNotImplementedException() {
		String id = "id";
		assertThrows(NotImplementedException.class, () -> service.findById(id));
	}

	@Test
	void findAll_ShouldThrowNotImplementedException() {
		assertThrows(NotImplementedException.class, service::findAll);
	}
}
