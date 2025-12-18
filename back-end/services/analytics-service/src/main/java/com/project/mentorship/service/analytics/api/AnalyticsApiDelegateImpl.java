package com.project.mentorship.service.analytics.api;

import com.project.mentorship.contract.analytics.api.AnalyticsApiDelegate;
import com.project.mentorship.contract.analytics.model.StatisticsDto;
import com.project.mentorship.lib.pattern.BaseService;
import com.project.mentorship.service.analytics.domain.Statistics;
import com.project.mentorship.service.analytics.mapper.StatisticsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalyticsApiDelegateImpl implements AnalyticsApiDelegate {

	private final BaseService<Statistics> statisticsService;
	private final StatisticsMapper statisticsMapper;

	@Override
	public ResponseEntity<StatisticsDto> createStatistics(StatisticsDto request) {
		Statistics statistics = statisticsMapper.mapToDomain(request);
		Statistics created = statisticsService.create(statistics);
		StatisticsDto response = statisticsMapper.mapToDto(created);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}