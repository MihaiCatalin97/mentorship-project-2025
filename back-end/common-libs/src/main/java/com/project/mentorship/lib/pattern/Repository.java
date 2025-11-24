package com.project.mentorship.lib.pattern;

import java.util.List;
import java.util.Optional;

public interface Repository<T, ID> {
	default T save(T entity) {
		throw new RuntimeException("Not implemented yet");
	}
	default T update(ID id, T entity) {
		throw new RuntimeException("Not implemented yet");
	}
	default void delete(ID id) {
		throw new RuntimeException("Not implemented yet");
	}
	default Optional<T> findById(ID id) {
		throw new RuntimeException("Not implemented yet");
	}
	default List<T> findAll() {
		throw new RuntimeException("Not implemented yet");
	}
}
