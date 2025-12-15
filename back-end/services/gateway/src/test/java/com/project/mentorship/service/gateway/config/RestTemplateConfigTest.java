package com.project.mentorship.service.gateway.config;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

class RestTemplateConfigTest {

	@Test
	void restTemplateBean_shouldNotBeNull() {
		RestTemplateConfig restTemplateConfig = new RestTemplateConfig();

		RestTemplate restTemplate = restTemplateConfig.restTemplate();

		assertThat(restTemplate).isNotNull();
	}
}
