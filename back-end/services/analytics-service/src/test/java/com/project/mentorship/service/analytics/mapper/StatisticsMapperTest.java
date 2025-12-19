package com.project.mentorship.service.analytics.mapper;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.project.mentorship.contract.analytics.model.StatisticsDto;
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
		StatisticsDto statisticsDto = new StatisticsDto(date, 10, 250.0);

		// When
		Statistics statistics = statisticsMapper.mapToDomain(statisticsDto);

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
		StatisticsDto statisticsDto = null;

		// When
		Statistics statistics = statisticsMapper.mapToDomain(statisticsDto);

		// Then
		assertThat(statistics).isNull();
	}

	@Test
	void mapToDto_shouldMapFieldsCorrectly_whenStatisticsIsNotNull() {
		// Given
		OffsetDateTime date = OffsetDateTime.of(2025, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
		OffsetDateTime createdAt = OffsetDateTime.of(2025, 1, 1, 1, 0, 0, 0, ZoneOffset.UTC);

		Statistics statistics = new Statistics(UUID.randomUUID(), date, 10, 250.0, createdAt);

		// When
		StatisticsDto statisticsDto = statisticsMapper.mapToDto(statistics);

		// Then
		assertThat(statisticsDto).isNotNull();
		assertEquals(date, statisticsDto.getDate());
		assertEquals(10, statisticsDto.getTotalReservations());
		assertEquals(250.0, statisticsDto.getTotalRevenue());

	}

	@Test
	void mapToDto_shouldReturnNull_whenStatisticsIsNull() {
		// Given
		Statistics statistics = null;

		// When
		StatisticsDto statisticsDto = statisticsMapper.mapToDto(statistics);

		// Then
		assertThat(statisticsDto).isNull();
	}
}
