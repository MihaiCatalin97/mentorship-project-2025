package com.project.mentorship.service.analytics.mapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.project.mentorship.service.analytics.api.dto.StatisticsDto;
import com.project.mentorship.service.analytics.domain.Statistics;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class StatisticsMapperTest {

	private final StatisticsMapper statisticsMapper = new StatisticsMapper();

	@Test
	void mapToDomain_shouldMapFieldsCorrectly_whenDtoIsNotNull() {
		// Given
		OffsetDateTime date = OffsetDateTime.of(2025, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
		StatisticsDto dto = new StatisticsDto(null, date, 10, Double.valueOf(250.0), null);

		// When
		Statistics statistics = statisticsMapper.mapToDomain(dto);

		// Then
		assertThat(statistics).isNotNull();
		assertThat(statistics.getId()).isNull();
		assertThat(statistics.getCreatedAt()).isNull();
		assertThat(statistics.getDate()).isEqualTo(date);
		assertThat(statistics.getTotalReservations()).isEqualTo(10);
		assertThat(statistics.getTotalRevenue()).isEqualTo(250.0);
	}

	@Test
	void mapToDomain_shouldReturnNull_whenDtoIsNull() {
		// Given
		StatisticsDto dto = null;

		// When
		Statistics statistics = statisticsMapper.mapToDomain(dto);

		// Then
		assertThat(statistics).isNull();
	}

	@Test
	void mapToDto_shouldMapFieldsCorrectly_whenStatisticsIsNotNull() {
		// Given
		OffsetDateTime date = OffsetDateTime.of(2025, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
		OffsetDateTime createdAt = OffsetDateTime.of(2025, 1, 1, 1, 0, 0, 0, ZoneOffset.UTC);

		Statistics statistics = new Statistics(UUID.randomUUID(), date, 10, Double.valueOf(250.0), createdAt);

		// When
		StatisticsDto dto = statisticsMapper.mapToDto(statistics);

		// Then
		assertThat(dto).isNotNull();
		assertThat(dto.date()).isEqualTo(date);
		assertThat(dto.totalReservations()).isEqualTo(10);
		assertThat(dto.totalRevenue()).isEqualTo(250.0);
	}

	@Test
	void mapToDto_shouldReturnNull_whenStatisticsIsNull() {
		// Given
		Statistics statistics = null;

		// When
		StatisticsDto dto = statisticsMapper.mapToDto(statistics);

		// Then
		assertThat(dto).isNull();
	}
}
