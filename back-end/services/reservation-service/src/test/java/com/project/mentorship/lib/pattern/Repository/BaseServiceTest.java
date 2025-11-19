package com.project.mentorship.lib.pattern;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.project.mentorship.lib.pattern.Repository.BaseService;
import org.junit.jupiter.api.Test;

class BaseServiceTest {

    private final BaseService<Object> service = new BaseService<>() {
    };

    @Test
    void create_ShouldThrowRuntimeException() {
        assertThrows(RuntimeException.class, () -> service.create(new Object()));
    }

    @Test
    void update_ShouldThrowRuntimeException() {
        assertThrows(RuntimeException.class, () -> service.update(new Object()));
    }

    @Test
    void delete_ShouldThrowRuntimeException() {
        assertThrows(RuntimeException.class, () -> service.delete("id"));
    }

    @Test
    void findById_ShouldThrowRuntimeException() {
        assertThrows(RuntimeException.class, () -> service.findById("id"));
    }

    @Test
    void findAll_ShouldThrowRuntimeException() {
        assertThrows(RuntimeException.class, service::findAll);
    }
}
