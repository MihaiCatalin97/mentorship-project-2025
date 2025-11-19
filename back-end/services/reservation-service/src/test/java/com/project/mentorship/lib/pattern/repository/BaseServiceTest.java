package com.project.mentorship.lib.pattern.repository;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class BaseServiceTest {

    private final BaseService<Object> service = new BaseService<>() { };

    @Test
    void create_ShouldThrowNotImplementedException() {
        assertThrows(BaseService.NotImplementedException.class,
                () -> service.create(new Object()));
    }

    @Test
    void update_ShouldThrowNotImplementedException() {
        assertThrows(BaseService.NotImplementedException.class,
                () -> service.update(new Object()));
    }

    @Test
    void delete_ShouldThrowNotImplementedException() {
        assertThrows(BaseService.NotImplementedException.class,
                () -> service.delete("id"));
    }

    @Test
    void findById_ShouldThrowNotImplementedException() {
        assertThrows(BaseService.NotImplementedException.class,
                () -> service.findById("id"));
    }

    @Test
    void findAll_ShouldThrowNotImplementedException() {
        assertThrows(BaseService.NotImplementedException.class,
                service::findAll);
    }
}
