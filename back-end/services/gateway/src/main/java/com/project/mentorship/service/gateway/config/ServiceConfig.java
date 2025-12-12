package com.project.mentorship.service.gateway.config;

import lombok.Data;

@Data
public class ServiceConfig {
	private int port;
	private String host;
	private String path;
	private String name;
}
