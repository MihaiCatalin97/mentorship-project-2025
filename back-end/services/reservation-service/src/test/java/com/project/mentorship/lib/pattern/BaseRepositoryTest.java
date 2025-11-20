package com.project.mentorship.lib.pattern;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.project.mentorship.lib.exception.NotImplementedException;
import org.junit.jupiter.api.Test;

class BaseRepositoryTest {

	private final BaseRepository<Object> repository = new BaseRepository<>() {
	};

	@Test
	void save_ShouldThrowNotImplementedException() {
		assertThrows(NotImplementedException.class, () -> repository.save(new Object()));
	}

	@Test
	void update_ShouldThrowNotImplementedException() {
		assertThrows(NotImplementedException.class, () -> repository.update(new Object()));
	}

	@Test
	void delete_ShouldThrowNotImplementedException() {
		assertThrows(NotImplementedException.class, () -> repository.delete("id"));
	}

	@Test
	void findById_ShouldThrowNotImplementedException() {
		assertThrows(NotImplementedException.class, () -> repository.findById("id"));
	}

	@Test
	void findAll_ShouldThrowNotImplementedException() {
		assertThrows(NotImplementedException.class, repository::findAll);
	}
}
