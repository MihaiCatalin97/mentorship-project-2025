package com.project.mentorship.service.auth;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
class AuthServiceApplicationTest {

    @Test
    void mainMethodRuns() {
        assertDoesNotThrow(() -> AuthServiceApplication.main(new String[]{}));
    }
}

