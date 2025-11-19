package com.project.mentorship.lib.pattern.repository;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T> {

    class NotImplementedException extends RuntimeException {
        public NotImplementedException() {
            super("Method not implemented yet");
        }
    }
    default T save(T entity) {
        throw new NotImplementedException();
    }

    default T update(T entity) {
        throw new NotImplementedException();
    }

    default void delete(String id) {
        throw new NotImplementedException();
    }

    default Optional<T> findById(String id) {
        throw new NotImplementedException();
    }

    default List<T> findAll() {
        throw new NotImplementedException();
    }
}
