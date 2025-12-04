package com.project.mentorship.service.analytics.mapper;

import com.project.mentorship.service.analytics.api.dto.StatisticsDto;
import com.project.mentorship.service.analytics.domain.Statistics;
import org.springframework.stereotype.Component;

@Component
public final class StatisticsMapper {

	public Statistics mapToDomain(StatisticsDto statisticsDto) {
		if (statisticsDto == null) {
			return null;
		}

		return new Statistics(null, statisticsDto.date(), statisticsDto.totalReservations(),
				statisticsDto.totalRevenue(), null);
	}

	public StatisticsDto mapToDto(Statistics statistics) {
		if (statistics == null) {
			return null;
		}
		return new StatisticsDto(statistics.getId(), statistics.getDate(), statistics.getTotalReservations(),
				statistics.getTotalRevenue(), statistics.getCreatedAt());
	}
}
