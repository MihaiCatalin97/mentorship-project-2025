package com.project.mentorship.lib.pattern.repository;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BaseRepositoryTest {

    private final BaseRepository<Object> repository = new BaseRepository<>() { };

    @Test
    void save_ShouldThrowNotImplementedException() {
        assertThrows(BaseRepository.NotImplementedException.class,
                () -> repository.save(new Object()));
    }

    @Test
    void update_ShouldThrowNotImplementedException() {
        assertThrows(BaseRepository.NotImplementedException.class,
                () -> repository.update(new Object()));
    }

    @Test
    void delete_ShouldThrowNotImplementedException() {
        assertThrows(BaseRepository.NotImplementedException.class,
                () -> repository.delete("id"));
    }

    @Test
    void findById_ShouldThrowNotImplementedException() {
        assertThrows(BaseRepository.NotImplementedException.class,
                () -> repository.findById("id"));
    }

    @Test
    void findAll_ShouldThrowNotImplementedException() {
        assertThrows(BaseRepository.NotImplementedException.class,
                repository::findAll);
    }
}
