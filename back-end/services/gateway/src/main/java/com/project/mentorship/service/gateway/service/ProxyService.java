package com.project.mentorship.service.gateway.service;

import com.project.mentorship.service.gateway.config.GatewayProperties;
import com.project.mentorship.service.gateway.config.ServiceConfig;
import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProxyService {

	private final RestTemplate restTemplate;
	private final GatewayProperties gatewayProperties;

	public ResponseEntity<byte[]> forward(HttpMethod method, HttpServletRequest request, String body) {
		String requestURI = request.getRequestURI();
		String queryString = request.getQueryString();

		ServiceConfig targetService = findServiceForPath(requestURI);

		URI targetURI = null;

		try {
			targetURI = new URI("http", null, targetService.getHost(), targetService.getPort(), requestURI, queryString,
					null);
		} catch (URISyntaxException e) {
			log.error("Failed to build target URI", e);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("Invalid URI constructed for service.".getBytes());
		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);

		try {
			return restTemplate.exchange(targetURI, method, requestEntity, byte[].class);
		} catch (HttpStatusCodeException ex) {
			HttpHeaders responseHeaders = ex.getResponseHeaders();
			if (responseHeaders == null) {
				responseHeaders = new HttpHeaders();
			}

			return new ResponseEntity<>(ex.getResponseBodyAsByteArray(), responseHeaders, ex.getStatusCode());
		}
	}
	private ServiceConfig findServiceForPath(String path) {
		return gatewayProperties.getServices().stream()
				.filter(serviceConfig -> path.startsWith(serviceConfig.getPath())).findFirst()
				.orElseThrow(ServiceNotFoundException::new);
	}
}
