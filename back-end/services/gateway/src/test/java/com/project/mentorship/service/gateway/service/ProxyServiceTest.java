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
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	void forward_shouldReturnBadRequest_whenConstructedUriIsInvalid() {
		// given
		HttpServletRequest request = mock(HttpServletRequest.class);
		when(request.getRequestURI()).thenReturn("/analytics/statistics");
		when(request.getQueryString()).thenReturn("page=1&size=10");

		ServiceConfig badConfig = new ServiceConfig();
		badConfig.setHost("local host");
		badConfig.setPort(8081);
		badConfig.setPath("/analytics");

		when(gatewayProperties.getServices()).thenReturn(List.of(badConfig));

		// when
		ResponseEntity<byte[]> response = proxyService.forward(HttpMethod.GET, request, "{\"test\":true}");

		// then
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
		assertThat(new String(response.getBody())).isEqualTo("Invalid URI constructed for service.");

		verifyNoInteractions(restTemplate);
	}
}