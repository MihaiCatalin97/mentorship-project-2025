package com.project.mentorship.service.analytics.api;

import com.project.mentorship.lib.pattern.BaseService;
import com.project.mentorship.service.analytics.api.dto.StatisticsDto;
import com.project.mentorship.service.analytics.domain.Statistics;
import com.project.mentorship.service.analytics.mapper.StatisticsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistics")
public class StatisticsController {

	private final BaseService<Statistics> statisticsService;
	private final StatisticsMapper statisticsMapper;

	@PostMapping
	public ResponseEntity<StatisticsDto> createStatistics(@RequestBody StatisticsDto request) {
		Statistics statistics = statisticsMapper.mapToDomain(request);
		Statistics created = statisticsService.create(statistics);
		StatisticsDto response = statisticsMapper.mapToDto(created);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
