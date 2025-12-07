package com.project.mentorship.service.auth.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogExecutionTimeAspect {

	@Around("@annotation(com.project.mentorship.service.auth.annotations.LogExecutionTime)")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();

		try {
			return joinPoint.proceed();
		} finally {
			long timeTaken = System.currentTimeMillis() - start;
			log.info("{} executed in {} ms", joinPoint.getSignature().toShortString(), timeTaken);
		}
	}
}
