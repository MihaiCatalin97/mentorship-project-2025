package com.project.mentorship.service.gateway.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

class ServiceNotFoundExceptionTest {

	@Test
	void constructor_shouldCreateInstance() {
		ServiceNotFoundException serviceNotFoundException = new ServiceNotFoundException();

		assertThat(serviceNotFoundException).isNotNull().isInstanceOf(RuntimeException.class);
	}
}
