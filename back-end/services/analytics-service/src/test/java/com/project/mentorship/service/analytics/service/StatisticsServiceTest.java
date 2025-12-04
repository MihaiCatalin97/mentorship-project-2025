package com.project.mentorship.service.analytics.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.project.mentorship.service.analytics.domain.Statistics;
import com.project.mentorship.service.analytics.persistance.StatisticsRepository;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class StatisticsServiceTest {

	@Test
	void create_shouldPopulateSystemFieldsAndDelegateToRepository() {
		// Given
		StatisticsRepository statisticsRepository = Mockito.mock(StatisticsRepository.class);
		StatisticsService statisticsService = new StatisticsService(statisticsRepository);

		Statistics statistics = new Statistics(null, OffsetDateTime.of(2025, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC), 10,
				BigDecimal.valueOf(250), null);

		when(statisticsRepository.save(any(Statistics.class))).thenAnswer(invocation -> invocation.getArgument(0));

		// When
		Statistics createdStatistics = statisticsService.create(statistics);

		// Then
		verify(statisticsRepository, times(1)).save(statistics);
		assertThat(createdStatistics.getId()).isNotNull();
		assertThat(createdStatistics.getCreatedAt()).isNotNull();
	}
}
