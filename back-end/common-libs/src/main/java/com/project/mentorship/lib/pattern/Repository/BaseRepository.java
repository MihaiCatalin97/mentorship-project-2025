package com.project.mentorship.lib.pattern.Repository;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T> {

    default T save(T entity) {
        throw new RuntimeException("Not implemented yet");
    }

    default T update(T entity) {
        throw new RuntimeException("Not implemented yet");
    }

    default void delete(String id) {
        throw new RuntimeException("Not implemented yet");
    }

    default Optional<T> findById(String id) {
        throw new RuntimeException("Not implemented yet");
    }

    default List<T> findAll() {
        throw new RuntimeException("Not implemented yet");
    }
}