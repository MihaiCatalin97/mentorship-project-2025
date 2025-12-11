package com.project.mentorship.service.gateway.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.project.mentorship.service.gateway.config.GatewayProperties;
import com.project.mentorship.service.gateway.config.ServiceConfig;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
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
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
class ProxyServiceTest {

	@Mock
	private GatewayProperties gatewayProperties;

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private ProxyService proxyService;

	private ServiceConfig analyticsConfig;

	@BeforeEach
	void setUp() {
		analyticsConfig = new ServiceConfig();
		analyticsConfig.setName("analytics");
		analyticsConfig.setHost("localhost");
		analyticsConfig.setPort(8081);
		analyticsConfig.setPath("/analytics");
	}

	@Test
	void forward_shouldCallTargetServiceWithoutQueryParams() {
		// given
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setRequestURI("/analytics");
		request.setMethod("POST");

		when(gatewayProperties.getServices()).thenReturn(List.of(analyticsConfig));

		ResponseEntity<byte[]> expectedResponse = new ResponseEntity<>("ok".getBytes(), HttpStatus.CREATED);

		when(restTemplate.exchange(anyString(), eq(HttpMethod.POST), any(HttpEntity.class), eq(byte[].class)))
				.thenReturn(expectedResponse);

		String body = "{\"test\":true}";

		// when
		ResponseEntity<byte[]> result = proxyService.forward(HttpMethod.POST, request, body);

		// then
		assertThat(result).isSameAs(expectedResponse);

		ArgumentCaptor<String> urlCaptor = ArgumentCaptor.forClass(String.class);
		verify(restTemplate).exchange(urlCaptor.capture(), eq(HttpMethod.POST), any(HttpEntity.class),
				eq(byte[].class));

		assertThat(urlCaptor.getValue()).isEqualTo("http://localhost:8081/analytics");
	}

	@Test
	void forward_shouldIncludeQueryStringWhenPresent() {
		// given
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setRequestURI("/analytics/statistics");
		request.setQueryString("page=1&size=10");
		request.setMethod("GET");

		when(gatewayProperties.getServices()).thenReturn(List.of(analyticsConfig));

		ResponseEntity<byte[]> expectedResponse = new ResponseEntity<>("ok".getBytes(), HttpStatus.OK);

		when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(byte[].class)))
				.thenReturn(expectedResponse);

		// when
		proxyService.forward(HttpMethod.GET, request, null);

		// then
		ArgumentCaptor<String> urlCaptor = ArgumentCaptor.forClass(String.class);
		verify(restTemplate).exchange(urlCaptor.capture(), eq(HttpMethod.GET), any(HttpEntity.class), eq(byte[].class));

		assertThat(urlCaptor.getValue()).isEqualTo("http://localhost:8081/analytics/statistics?page=1&size=10");
	}

	@Test
	void forward_shouldThrowServiceNotFoundWhenNoMatchingService() {
		// given
		HttpServletRequest request = new MockHttpServletRequest();
		when(gatewayProperties.getServices()).thenReturn(List.of());

		// when / then
		assertThrows(ServiceNotFoundException.class, () -> proxyService.forward(HttpMethod.GET, request, null));
	}
}
