package com.project.mentorship.service.auth.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String hash(String raw){
        return passwordEncoder.encode(raw);
    }

    public boolean matches(String raw, String hashed){
        return passwordEncoder.matches(hashed, raw);
    }
}
