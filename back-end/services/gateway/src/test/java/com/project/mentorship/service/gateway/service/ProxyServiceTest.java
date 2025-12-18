package com.project.mentorship.service.gateway.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import com.project.mentorship.service.gateway.config.GatewayProperties;
import com.project.mentorship.service.gateway.config.ServiceConfig;
import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
class ProxyServiceTest {

	@Mock
	private RestTemplate restTemplate;

	@Mock
	private GatewayProperties gatewayProperties;

	@InjectMocks
	private ProxyService proxyService;

	@Test
	void forward_shouldCallTargetServiceAndReturnResponse() {
		// given
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn("/analytics/statistics");
		when(request.getQueryString()).thenReturn("page=1&size=10");

		ServiceConfig analyticsConfig = new ServiceConfig();
		analyticsConfig.setHost("localhost");
		analyticsConfig.setPort(8081);
		analyticsConfig.setPath("/analytics");

		when(gatewayProperties.getServices()).thenReturn(List.of(analyticsConfig));

		ResponseEntity<byte[]> expected = ResponseEntity.ok("ok".getBytes());

		when(restTemplate.exchange(any(URI.class), eq(HttpMethod.POST), any(HttpEntity.class), eq(byte[].class)))
				.thenReturn(expected);

		// when
		ResponseEntity<byte[]> result = proxyService.forward(HttpMethod.POST, request, "{\"test\":true}");

		// then
		assertThat(result).isSameAs(expected);

		ArgumentCaptor<URI> uriCaptor = ArgumentCaptor.forClass(URI.class);
		verify(restTemplate).exchange(uriCaptor.capture(), eq(HttpMethod.POST), any(HttpEntity.class),
				eq(byte[].class));

		URI captured = uriCaptor.getValue();
		assertThat(captured.getScheme()).isEqualTo("http");
		assertThat(captured.getHost()).isEqualTo("localhost");
		assertThat(captured.getPort()).isEqualTo(8081);
		assertThat(captured.getPath()).isEqualTo("/analytics/statistics");
		assertThat(captured.getQuery()).isEqualTo("page=1&size=10");
	}

	@Test
	void forward_shouldThrowServiceNotFoundException_whenNoMatchingService() {
		// given
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn("/analytics/statistics");
		when(request.getQueryString()).thenReturn(null);

		ServiceConfig authConfig = new ServiceConfig();
		authConfig.setHost("localhost");
		authConfig.setPort(8080);
		authConfig.setPath("/auth");

		when(gatewayProperties.getServices()).thenReturn(List.of(authConfig));

		// when + then
		assertThrows(ServiceNotFoundException.class, () -> proxyService.forward(HttpMethod.GET, request, null));

		verifyNoInteractions(restTemplate);
	}

	@Test
	void forward_shouldPropagateDownstreamHttpError() {
		// given
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn("/auth/idk");
		when(request.getQueryString()).thenReturn(null);

		ServiceConfig authConfig = new ServiceConfig();
		authConfig.setHost("localhost");
		authConfig.setPort(8082);
		authConfig.setPath("/auth");

		when(gatewayProperties.getServices()).thenReturn(List.of(authConfig));

		String errorBody = "{\"message\":\"User not found\"}";
		HttpClientErrorException notFoundException = HttpClientErrorException.create(HttpStatus.NOT_FOUND, "Not Found",
				HttpHeaders.EMPTY, errorBody.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);

		when(restTemplate.exchange(any(URI.class), eq(HttpMethod.GET), any(HttpEntity.class), eq(byte[].class)))
				.thenThrow(notFoundException);

		// when
		ResponseEntity<byte[]> response = proxyService.forward(HttpMethod.GET, request, null);

		// then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(new String(response.getBody())).isEqualTo(errorBody);
	}
	@Test
	void forward_shouldReturnBadRequest_whenUriIsInvalid() {
		// given
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn("/auth/login");
		when(request.getQueryString()).thenReturn("a=1");

		ServiceConfig badConfig = new ServiceConfig();
		badConfig.setHost("invalid host");
		badConfig.setPort(8080);
		badConfig.setPath("/auth");

		when(gatewayProperties.getServices()).thenReturn(List.of(badConfig));

		// when
		ResponseEntity<byte[]> response = proxyService.forward(HttpMethod.GET, request, null);

		// then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(new String(response.getBody(), StandardCharsets.UTF_8))
				.isEqualTo("Invalid URI constructed for service.");

		verifyNoInteractions(restTemplate);
	}

	@Test
	void forward_shouldPropagateDownstreamHttpError_whenNoResponseHeaders() {
		// given
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn("/auth/idk");
		when(request.getQueryString()).thenReturn(null);

		ServiceConfig authConfig = new ServiceConfig();
		authConfig.setHost("localhost");
		authConfig.setPort(8082);
		authConfig.setPath("/auth");

		when(gatewayProperties.getServices()).thenReturn(List.of(authConfig));

		String errorBody = "{\"message\":\"User not found\"}";

		HttpStatusCodeException exceptionWithoutHeaders = new HttpClientErrorException(HttpStatus.NOT_FOUND,
				"Not Found", null, errorBody.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);

		when(restTemplate.exchange(any(URI.class), eq(HttpMethod.GET), any(HttpEntity.class), eq(byte[].class)))
				.thenThrow(exceptionWithoutHeaders);

		// when
		ResponseEntity<byte[]> response = proxyService.forward(HttpMethod.GET, request, null);

		// then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(new String(response.getBody(), StandardCharsets.UTF_8)).isEqualTo(errorBody);
		assertThat(response.getHeaders()).isNotNull();
	}

}
