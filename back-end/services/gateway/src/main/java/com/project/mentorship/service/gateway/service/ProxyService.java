package com.project.mentorship.service.gateway.service;

import com.project.mentorship.service.gateway.config.GatewayProperties;
import com.project.mentorship.service.gateway.config.ServiceConfig;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ProxyService {

	private final RestTemplate restTemplate;
	private final GatewayProperties gatewayProperties;

	public ResponseEntity<byte[]> forward(HttpMethod method, HttpServletRequest request, String body) {
		String requestURI = request.getRequestURI();
		String queryString = request.getQueryString();

		ServiceConfig targetService = findServiceForPath(requestURI);

		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append("http://").append(targetService.getHost()).append(":").append(targetService.getPort())
				.append(requestURI);

		if (queryString != null && !queryString.isEmpty()) {
			urlBuilder.append("?").append(queryString);
		}

		String targetURL = urlBuilder.toString();

		HttpHeaders headers = new HttpHeaders();

		HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
		headers.setContentType(MediaType.APPLICATION_JSON);

		return restTemplate.exchange(targetURL, method, requestEntity, byte[].class);
	}
	private ServiceConfig findServiceForPath(String path) {
		List<ServiceConfig> services = gatewayProperties.getServices();

		if (services != null) {
			for (ServiceConfig service : services) {
				if (path.startsWith(service.getPath())) {
					return service;
				}
			}
		}
		throw new ServiceNotFoundException();
	}
}
