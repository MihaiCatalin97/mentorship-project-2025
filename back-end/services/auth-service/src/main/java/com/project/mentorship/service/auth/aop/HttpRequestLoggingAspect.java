package com.project.mentorship.service.auth.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
@Component
public class HttpRequestLoggingAspect {

	@Before("within(@org.springframework.stereotype.Controller *) || "
			+ "within(@org.springframework.web.bind.annotation.RestController *)")
	public void logHttpRequest(JoinPoint joinPoint) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

		HttpServletRequest request = attributes.getRequest();

		log.info("Incoming request: {} {}", request.getMethod(), request.getRequestURI());
	}
}
