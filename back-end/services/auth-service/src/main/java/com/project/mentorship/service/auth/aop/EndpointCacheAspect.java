package com.project.mentorship.service.auth.aop;

import com.project.mentorship.service.auth.annotations.EndpointCache;
import com.project.mentorship.service.auth.cache.SimpleCache;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class EndpointCacheAspect {

	@Around("@annotation(endpointCache)")
	public Object cacheEndpoint(ProceedingJoinPoint pjp, EndpointCache endpointCache) throws Throwable {
		String key = generateKey(pjp);

		Object cached = SimpleCache.get(key);
		if (cached != null) {
			log.info("Cache hit for {}", key);
			return cached;
		}

		log.info("Cache miss for {} → executing endpoint", key);

		Object result = pjp.proceed();

		SimpleCache.put(key, result, endpointCache.ttlSeconds());

		return result;
	}

	private String generateKey(ProceedingJoinPoint pjp) {
		return pjp.getSignature().toLongString() + Arrays.toString(pjp.getArgs());
	}
}
