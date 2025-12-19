package com.project.mentorship.service.analytics.mapper;

import com.project.mentorship.contract.analytics.model.StatisticsDto;
import com.project.mentorship.service.analytics.domain.Statistics;
import org.springframework.stereotype.Component;

@Component
public final class StatisticsMapper {

	public Statistics mapToDomain(StatisticsDto statisticsDto) {
		if (statisticsDto == null) {
			return null;
		}

		return new Statistics(null, statisticsDto.getDate(), statisticsDto.getTotalReservations(),
				statisticsDto.getTotalRevenue(), null);
	}

	public StatisticsDto mapToDto(Statistics statistics) {
		if (statistics == null) {
			return null;
		}

		StatisticsDto statisticsDto = new StatisticsDto();
		statisticsDto.setId(statistics.getId());
		statisticsDto.setDate(statistics.getDate());
		statisticsDto.setTotalReservations(statistics.getTotalReservations());
		statisticsDto.setTotalRevenue(statistics.getTotalRevenue());
		statisticsDto.setCreatedAt(statistics.getCreatedAt());
		return statisticsDto;
	}
}
