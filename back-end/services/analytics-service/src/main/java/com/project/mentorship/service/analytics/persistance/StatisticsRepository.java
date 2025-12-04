package com.project.mentorship.service.analytics.persistance;

import com.project.mentorship.lib.pattern.BaseRepository;
import com.project.mentorship.service.analytics.domain.Statistics;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StatisticsRepository implements BaseRepository<Statistics> {

	private final List<Statistics> statistics = new ArrayList<>();

	@Override
	public Statistics save(Statistics statistics) {
		this.statistics.add(statistics);
		return statistics;
	}
}
