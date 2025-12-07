package com.project.mentorship.service.auth.aop;

import static java.util.Objects.isNull;

import com.project.mentorship.service.auth.annotations.RequiredRoles;
import com.project.mentorship.service.auth.exception.ForbiddenException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
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
public class RequiredRolesAspect {

	@Before("@annotation(requiredRoles)")
	public void checkRoles(JoinPoint joinPoint, RequiredRoles requiredRolesAnnotation) {
		String header = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()
				.getHeader("x-user-roles");
		Collection<String> requiredRoles = Arrays.asList(requiredRolesAnnotation.value());
		Collection<String> userRoles;

		if (isNull(header)) {
			userRoles = Collections.emptyList();
		} else {
			userRoles = Arrays.asList(header.split(","));
		}

		if (requiredRoles.stream().noneMatch(userRoles::contains)) {
			log.warn("Access denied: none of the required roles '{}' are not present", requiredRoles);
			throw new ForbiddenException("Missing required roles: " + requiredRoles);
		}

		log.info("Request authorized for roles: {}", userRoles);
	}
}
