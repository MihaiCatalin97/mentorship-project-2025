package com.project.mentorship.service.analytics.service;

import com.project.mentorship.lib.pattern.BaseRepository;
import com.project.mentorship.lib.pattern.BaseService;
import com.project.mentorship.service.analytics.domain.Statistics;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticsService implements BaseService<Statistics> {
	private final BaseRepository<Statistics> statisticsRepository;

	@Override
	public Statistics create(Statistics statistics) {
		statistics.setId(UUID.randomUUID());
		statistics.setCreatedAt(OffsetDateTime.now(ZoneOffset.UTC));
		return statisticsRepository.save(statistics);
	}
}
