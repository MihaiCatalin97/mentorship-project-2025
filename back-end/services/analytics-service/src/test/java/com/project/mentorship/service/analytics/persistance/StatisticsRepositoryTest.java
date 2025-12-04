package com.project.mentorship.service.analytics.persistance;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.project.mentorship.service.analytics.domain.Statistics;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;
import org.junit.jupiter.api.Test;

public class StatisticsRepositoryTest {

	@Test
	void save_shouldStoreStatisticsInMemory() {
		// Given
		StatisticsRepository statisticsRepository = new StatisticsRepository();
		Statistics statistics = new Statistics(UUID.randomUUID(),
				OffsetDateTime.of(2025, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC), 10, BigDecimal.valueOf(250),
				OffsetDateTime.of(2025, 1, 1, 1, 0, 0, 0, ZoneOffset.UTC));

		// When
		Statistics savedStatistics = statisticsRepository.save(statistics);

		// Then
		assertThat(savedStatistics).isSameAs(statistics);
	}
}
