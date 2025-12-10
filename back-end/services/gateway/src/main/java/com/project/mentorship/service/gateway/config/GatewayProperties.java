package com.project.mentorship.service.gateway.config;

import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "gateway")
public class GatewayProperties {
	private List<ServiceConfig> services;

}
