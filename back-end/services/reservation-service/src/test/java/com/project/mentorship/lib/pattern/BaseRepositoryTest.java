package com.project.mentorship.lib.pattern;

import com.project.mentorship.lib.exception.NotImplementedException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class BaseRepositoryTest {

    private final BaseRepository<Object> repository = new BaseRepository<>() { };

    @Test
    void save_ShouldThrowNotImplementedException() {
        Object entity = new Object();
        assertThrows(NotImplementedException.class,
                () -> repository.save(entity));
    }

    @Test
    void update_ShouldThrowNotImplementedException() {
        Object entity = new Object();
        assertThrows(NotImplementedException.class,
                () -> repository.update(entity));
    }

    @Test
    void delete_ShouldThrowNotImplementedException() {
        String id = "id";
        assertThrows(NotImplementedException.class,
                () -> repository.delete(id));
    }

    @Test
    void findById_ShouldThrowNotImplementedException() {
        String id = "id";
        assertThrows(NotImplementedException.class,
                () -> repository.findById(id));
    }

    @Test
    void findAll_ShouldThrowNotImplementedException() {
        assertThrows(NotImplementedException.class, repository::findAll);
    }
}
