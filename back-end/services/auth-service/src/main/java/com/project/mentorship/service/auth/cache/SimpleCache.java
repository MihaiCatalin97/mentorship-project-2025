package com.project.mentorship.service.auth.cache;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimpleCache {

	private static final Map<String, CacheEntry> CACHE = new ConcurrentHashMap<>();

	public static Object get(String key) {
		CacheEntry entry = CACHE.get(key);
		if (entry == null)
			return null;

		if (Instant.now().isAfter(entry.expiresAt())) {
			CACHE.remove(key);
			return null;
		}

		return entry.value();
	}

	public static void put(String key, Object value, long ttlSeconds) {
		Instant expiresAt = Instant.now().plusSeconds(ttlSeconds);
		CACHE.put(key, new CacheEntry(value, expiresAt));
	}

	private record CacheEntry(Object value, Instant expiresAt) {
	}
}
