package com.project.mentorship.service.gateway.web;

import com.project.mentorship.service.gateway.service.ProxyService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GatewayController {

	private final ProxyService proxyService;

	@RequestMapping(value = "/**", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
			RequestMethod.PATCH, RequestMethod.DELETE})
	public ResponseEntity<byte[]> proxy(HttpMethod method, HttpServletRequest request,
			@RequestBody(required = false) String body) {

		return proxyService.forward(method, request, body);
	}
}
