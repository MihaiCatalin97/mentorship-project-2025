package com.project.mentorship.service.gateway.web;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.project.mentorship.service.gateway.service.ProxyService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class GatewayControllerTest {

	@Mock
	private ProxyService proxyService;

	@InjectMocks
	private GatewayController gatewayController;

	@Test
	void proxy_shouldDelegateToProxyService() {
		// given
		HttpServletRequest request = mock(HttpServletRequest.class);
		String body = "{\"test\":true}";

		ResponseEntity<byte[]> expected = ResponseEntity.ok("ok".getBytes());

		when(proxyService.forward(eq(HttpMethod.POST), eq(request), eq(body))).thenReturn(expected);

		// when
		ResponseEntity<byte[]> result = gatewayController.proxy(HttpMethod.POST, request, body);

		// then
		verify(proxyService).forward(HttpMethod.POST, request, body);
		assertThat(result).isSameAs(expected);
	}
}
