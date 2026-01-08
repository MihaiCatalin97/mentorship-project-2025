package com.project.mentorship.service.auth.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = {"com.project.mentorship.contract.auth.api", "com.project.mentorship.service.auth"})
@Configuration
public class AuthSpringConfiguration {
}