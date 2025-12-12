package com.project.mentorship.service.gateway.config;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class GatewayPropertiesTest {

	@Test
	void shouldStoreServicesList() {
		ServiceConfig service = new ServiceConfig();
		service.setName("analytics");
		service.setHost("localhost");
		service.setPort(8081);
		service.setPath("/analytics");

		GatewayProperties properties = new GatewayProperties();
		properties.setServices(List.of(service));

		assertThat(properties.getServices()).hasSize(1).first().extracting(ServiceConfig::getPath)
				.isEqualTo("/analytics");
	}
}
