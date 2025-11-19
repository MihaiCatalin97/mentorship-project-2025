package com.project.mentorship.lib.pattern.Repository;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.project.mentorship.lib.pattern.Repository.BaseRepository;
import org.junit.jupiter.api.Test;

class BaseRepositoryTest {

    private final BaseRepository<Object> repository = new BaseRepository<>() {

    };

    @Test
    void save_ShouldThrowRuntimeException() {
        assertThrows(RuntimeException.class, () -> repository.save(new Object()));
    }

    @Test
    void update_ShouldThrowRuntimeException() {
        assertThrows(RuntimeException.class, () -> repository.update(new Object()));
    }

    @Test
    void delete_ShouldThrowRuntimeException() {
        assertThrows(RuntimeException.class, () -> repository.delete("id"));
    }

    @Test
    void findById_ShouldThrowRuntimeException() {
        assertThrows(RuntimeException.class, () -> repository.findById("id"));
    }

    @Test
    void findAll_ShouldThrowRuntimeException() {
        assertThrows(RuntimeException.class, repository::findAll);
    }
}
