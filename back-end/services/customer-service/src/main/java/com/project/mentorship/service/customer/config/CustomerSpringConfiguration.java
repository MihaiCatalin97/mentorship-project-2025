package com.project.mentorship.service.customer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = "com.project.mentorship.contract.customer.api")
@Configuration
public class CustomerSpringConfiguration {
}
